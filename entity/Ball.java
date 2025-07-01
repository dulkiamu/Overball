package entity;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Ball {
    GamePanel gp;

    // Position und Geschwindigkeit
    public double x, y;
    public double velocityX, velocityY;

    // Ball-Eigenschaften
    public int size = 128; // Ball-Größe
    public double gravity = 0.3; // Reduzierte Gravitation
    public double bounceDamping = 0.8; // Dämpfung beim Aufprall
    public double friction = 1.0; // Kein Luftwiderstand für konstante Geschwindigkeit
    public double maxSpeed = 9.0; // Maximale Geschwindigkeit
    public double minSpeed = 6.0; // Minimale Geschwindigkeit

    // Bild für den Ball
    BufferedImage ballImage;

    public Ball(GamePanel gp) {
        this.gp = gp;
        setDefaultValues();
        getBallImage();
    }

    public void setDefaultValues() {
        // Ball startet in der Mitte oben
        x = gp.screenWidth / 2 - size / 2;
        y = 100;
        velocityX = 3; // Langsamere Anfangsgeschwindigkeit
        velocityY = 0; // Anfangsgeschwindigkeit vertikal
    }

    public void getBallImage() {
        try {
            ballImage = ImageIO.read(getClass().getResourceAsStream("/ball/DerBall.png"));
            System.out.println("Ball-Bild geladen (oder Fallback zu Kreis)");
        } catch (Exception e) {
            System.out.println("Kein Ball-Bild gefunden, verwende Kreis");
            ballImage = null;
        }
    }

    public void update() {
        // Gravitation anwenden
        velocityY += gravity;

        // Position aktualisieren
        x += velocityX;
        y += velocityY;

        // Geschwindigkeit kontrollieren - konstante horizontale Geschwindigkeit
        normalizeSpeed();

        // Verhindere, dass Ball zu langsam wird und "klebt"
        if (Math.abs(velocityX) < 0.1) velocityX = 0;
        if (Math.abs(velocityY) < 0.1 && y >= gp.screenHeight - size - 5) velocityY = 0;

        // Kollision mit Wänden (links und rechts)
        if (x <= 0) {
            x = 0;
            velocityX = Math.abs(velocityX); // Immer positive Geschwindigkeit nach rechts
            normalizeHorizontalSpeed();
        }
        if (x >= gp.screenWidth - size) {
            x = gp.screenWidth - size;
            velocityX = -Math.abs(velocityX); // Immer negative Geschwindigkeit nach links
            normalizeHorizontalSpeed();
        }

        // Kollision mit Boden
        if (y >= gp.screenHeight - size) {
            y = gp.screenHeight - size;
            velocityY = -Math.abs(velocityY) * bounceDamping;
            normalizeHorizontalSpeed(); // Horizontale Geschwindigkeit konstant halten
        }

        // Kollision mit Decke
        if (y <= 0) {
            y = 0;
            velocityY = Math.abs(velocityY) * bounceDamping;
        }

        // Kollision mit Netz (in der Mitte bei x=704)
        checkNetCollision();
    }

    private void checkNetCollision() {
        int netX = 704;
        int netWidth = gp.tileSize;
        int netTopY = 448;
        int netBottomY = gp.screenHeight;

        // Prüfe ob Ball das Netz berührt
        if (x + size > netX && x < netX + netWidth && y + size > netTopY && y < netBottomY) {
            // Ball kommt von links
            if (velocityX > 0 && x < netX) {
                x = netX - size;
                velocityX = -maxSpeed; // Konstante Geschwindigkeit nach links
            }
            // Ball kommt von rechts
            else if (velocityX < 0 && x > netX) {
                x = netX + netWidth;
                velocityX = maxSpeed; // Konstante Geschwindigkeit nach rechts
            }
        }
    }

    // Kollision mit Spieler prüfen - verbesserte Version
    public void checkPlayerCollision(Player player) {
        checkPlayerCollisionGeneric(player.x, player.y, gp.tileSize * 4, gp.tileSize * 4);
    }

    public void checkPlayerCollision(Player2 player) {
        checkPlayerCollisionGeneric(player.x, player.y, gp.tileSize * 4, gp.tileSize * 4);
    }

    private void checkPlayerCollisionGeneric(int playerX, int playerY, int playerWidth, int playerHeight) {
        // Ball-Grenzen
        double ballLeft = x;
        double ballRight = x + size;
        double ballTop = y;
        double ballBottom = y + size;

        // Spieler-Grenzen
        double playerLeft = playerX;
        double playerRight = playerX + playerWidth;
        double playerTop = playerY;
        double playerBottom = playerY + playerHeight;

        // Prüfe ob Kollision stattfindet
        if (ballRight > playerLeft && ballLeft < playerRight && 
        ballBottom > playerTop && ballTop < playerBottom) {

            // Ball-Zentrum
            double ballCenterX = x + size / 2;
            double ballCenterY = y + size / 2;

            // Spieler-Zentrum
            double playerCenterX = playerX + playerWidth / 2;
            double playerCenterY = playerY + playerHeight / 2;

            // Überlappung berechnen
            double overlapX = Math.min(ballRight - playerLeft, playerRight - ballLeft);
            double overlapY = Math.min(ballBottom - playerTop, playerBottom - ballTop);

            // Bestimme Kollisionsrichtung basierend auf kleinerer Überlappung
            if (overlapX < overlapY) {
                // Horizontale Kollision
                if (ballCenterX < playerCenterX) {
                    // Ball links vom Spieler
                    x = playerLeft - size - 2;
                    velocityX = Math.abs(velocityX) * -1.2; // Nach links mit mehr Kraft
                } else {
                    // Ball rechts vom Spieler
                    x = playerRight + 2;
                    velocityX = Math.abs(velocityX) * 1.2; // Nach rechts mit mehr Kraft
                }
                // Leichter Aufwärtsimpuls
                velocityY -= 1;
            } else {
                // Vertikale Kollision
                if (ballCenterY < playerCenterY) {
                    // Ball über dem Spieler - Ball nach oben schlagen
                    y = playerTop - size - 2;
                    velocityY = -8; // Konstante Sprungkraft nach oben

                    // Horizontale Geschwindigkeit basierend auf Auftreffpunkt
                    double hitPosition = (ballCenterX - playerCenterX) / (playerWidth / 2);
                    velocityX = hitPosition * maxSpeed; // Richtung basierend auf Auftreffpunkt
                    normalizeHorizontalSpeed(); // Konstante horizontale Geschwindigkeit
                } else {
                    // Ball unter dem Spieler - Ball nach unten drücken
                    y = playerBottom + 2;
                    velocityY = 3; // Konstante Geschwindigkeit nach unten
                }
            }

            // Sicherstellen dass horizontale Geschwindigkeit konstant bleibt
            normalizeHorizontalSpeed();
        }
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(ballImage, (int)x, (int)y, size, size, null);

    }

    // Ball zurücksetzen (für Punkte system später)
    public void reset() {
        setDefaultValues();
    }

    // Hilfsmethoden für konstante Geschwindigkeit
    private void normalizeSpeed() {
        // Gesamtgeschwindigkeit berechnen
        double totalSpeed = Math.sqrt(velocityX * velocityX + velocityY * velocityY);

        // Nur horizontale Geschwindigkeit normalisieren (vertikale durch Gravitation)
        if (Math.abs(velocityX) > maxSpeed) {
            velocityX = velocityX > 0 ? maxSpeed : -maxSpeed;
        }

        // Maximale vertikale Geschwindigkeit begrenzen
        if (Math.abs(velocityY) > maxSpeed * 2) {
            velocityY = velocityY > 0 ? maxSpeed * 2 : -maxSpeed * 2;
        }
    }

    private void normalizeHorizontalSpeed() {
        if (Math.abs(velocityX) < minSpeed) {
            velocityX = velocityX > 0 ? minSpeed : -minSpeed;
        }
        if (Math.abs(velocityX) > maxSpeed) {
            velocityX = velocityX > 0 ? maxSpeed : -maxSpeed;
        }
    }
}


