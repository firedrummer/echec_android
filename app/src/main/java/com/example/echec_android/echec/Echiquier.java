package com.example.echec_android.echec;


import com.example.echec_android.echec.Piece.Couleur;
import com.example.echec_android.echec.Piece.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Classe constituant un échiquier
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class Echiquier {
    //Variable pour effectuer des sauts de ligne
    private static final String SAUT_LIGNE = System.lineSeparator();

    /**
     * L'échiquier représenté sous forme de hashmap
     */
    private LinkedHashMap<String, Piece> m_echiquier = new LinkedHashMap<>();

    /**
     * Dernier déplacement d'une Piece
     */
    private String dernierTourJouer = "";

    /**
     * Dernier déplacement d'une Piece avec 1 si c'est sont premier déplacement
     * et 0 dans le cas contraire, la valeur est du String est vide et le nombre est 3
     * si le dernier deplacement n'est pas effectuer par un pion
     */
    private int estDernierTourJouerPion = 3;

    /**
     * Constructeur sans paramètres
     */
    public Echiquier() {
    }

    /**
     * Méthode qui ajoute une piece blanche à une coordonnée indiquée
     *
     * @param p_coordonnee position de placement pour le piece à ajouter
     * @param piece        pièce à ajouter dans l'échiquier
     */
    public void ajouterPiece(String p_coordonnee, Piece piece) {
        m_echiquier.put(p_coordonnee, piece);
    }

    /**
     * Vide l'échiquier
     */
    public void viderEchiquier() {
        m_echiquier.clear();
    }

    /**
     * Gets nombre pieces.
     *
     * @return le nombre de pieces présents sur l'échiquier
     */
    public int getNombrePieces() {
        return m_echiquier.size();
    }

    /**
     * Obtient le piece à la position en paramètre
     *
     * @param p_position position d'un piece dans l'échiquier
     * @return le piece a la position déclarée en paramètre
     */
    public Piece getPiece(String p_position) {
        return m_echiquier.get(p_position);
    }

    /**
     * Obtient la pièce à la position indiquée
     * <p>
     * ps on n'était pas certain si getPiece(String p_position) était suffisant
     * alors on a ajouter cette méthode
     *
     * @param p_position position de la pièce
     * @return le style de pièce
     */
    public String getPieceEnTexte(String p_position) {
        Piece piece = m_echiquier.get(p_position);

        return (piece != null ? piece.getType().toString() : null) + " " +
                ((piece != null && piece.estNoir()) ? "NOIRE" : "BLANC");
    }

    /**
     * Initialise l'échiquier
     */
    public void initialiser() {
        // Rangée 1
        ajouterPiece("a1", new Tour(Couleur.BLANC));
        ajouterPiece("b1", new Cavalier(Couleur.BLANC));
        ajouterPiece("c1", new Fou(Couleur.BLANC));
        ajouterPiece("d1", new Reine(Couleur.BLANC));
        ajouterPiece("e1", new Roi(Couleur.BLANC));
        ajouterPiece("f1", new Fou(Couleur.BLANC));
        ajouterPiece("g1", new Cavalier(Couleur.BLANC));
        ajouterPiece("h1", new Tour(Couleur.BLANC));

        for (char i = 'a'; i < 'i'; i++) {
            // Rangée 2
            ajouterPiece(i + "2", new Pion(Couleur.BLANC));

            // Rangée 7
            ajouterPiece(i + "7", new Pion(Couleur.NOIR));
        }

        // Rangée 8
        ajouterPiece("a8", new Tour(Couleur.NOIR));
        ajouterPiece("b8", new Cavalier(Couleur.NOIR));
        ajouterPiece("c8", new Fou(Couleur.NOIR));
        ajouterPiece("d8", new Reine(Couleur.NOIR));
        ajouterPiece("e8", new Roi(Couleur.NOIR));
        ajouterPiece("f8", new Fou(Couleur.NOIR));
        ajouterPiece("g8", new Cavalier(Couleur.NOIR));
        ajouterPiece("h8", new Tour(Couleur.NOIR));
    }

    /**
     * Represente les pièces actuelles dans l'echiquier sous forme d'une string
     *
     * @return la representation graphique de l'echiquier
     */
    public String obtenirRepresentation() {
        StringBuilder representation = new StringBuilder();

        for (int j = 1; j < 9; j++) {
            for (char i = 'a'; i < 'i'; i++) {
                if (m_echiquier.containsKey("" + i + j)) {
                    representation.append(getPiece("" + i + j).obtenirRepresentation());
                } else {
                    representation.append("X");
                }
            }

            if (j < 8)
                representation.append(SAUT_LIGNE);
        }
        return representation.toString();
    }

    /**
     * Compte du pointage des pièces de la couleur indiquer en paramètre
     *
     * @param p_couleur couleur des pièces à savoir le pointage
     * @return the double
     */
    public double compterPointsEchiquier(Couleur p_couleur) {
        double nombrePoint = 0;
        Collection<Piece> pieces = m_echiquier.values();

        for (Piece piece : pieces) {
            if (piece.getCouleur() == p_couleur)
                nombrePoint += piece.obtenirPointagePiece();
        }
        return nombrePoint;
    }

    /**
     * Obtient le nombre de pièces selon la couleur et le type spéicifié
     *
     * @param p_type    type de la pièce
     * @param p_couleur couleur de la pièce
     * @return le nombre de pièces selon le type / couleur spéficié
     */
    public int obtenirNombrePieceSelonCouleurEtType(Type p_type, Couleur p_couleur) {
        int nombrePieces = 0; // nombre de pieces du type et couleur spécifiés en paramètre
        Collection<Piece> pieces = m_echiquier.values();

        for (Piece piece : pieces) {
            if (piece.getCouleur() == p_couleur && piece.getType() == p_type)
                nombrePieces += 1;
        }
        return nombrePieces;
    }

    /**
     * Méthode qui retourne les pièces selon leur couleur
     *
     * @param p_couleur couleur de la pièce retournée
     * @return la pièce
     */
    private LinkedHashMap<String, Piece> getPiecesSelonCouleur(Couleur p_couleur) {
        LinkedHashMap<String, Piece> pieces = new LinkedHashMap<>();

        for (Map.Entry<String, Piece> position : m_echiquier.entrySet()) {
            if (position.getValue().getCouleur() == p_couleur)
                pieces.put(position.getKey(), position.getValue());
        }

        return pieces;
    }

    /**
     * Donne les possibilitées de tour possible à jouer pour une couleur
     *
     * @param p_couleur couleur des pieces à avoir les mouvements possibles
     * @return une LinkecHashMap des coordonnées de départ et de fin qui corresponde chacun à un mouvement possible
     */
    private LinkedHashMap<String, String> getToursPossibleSelonCouleur(Couleur p_couleur) {
        LinkedHashMap<String, String> tourPossible = new LinkedHashMap<>();
        LinkedHashMap<String, Piece> pieces = getPiecesSelonCouleur(p_couleur);

        for (Map.Entry<String, Piece> position : pieces.entrySet()) {
            tourPossible.putAll(getToursPossibleSelonPiece(position.getValue(), position.getKey()));
        }

        return tourPossible;
    }

    /**
     * Donne les possibilitées de tour possible à jouer pour une pièce
     *
     * @param p_piece      pièce dont on veut savoir les mouvements possibles
     * @param p_coordonnee coordonnée de départ de la pièce
     * @return une LinkecHashMap des coordonnées de départ et de fin qui corresponde chacun à un mouvement possible
     */
    public LinkedHashMap<String, String> getToursPossibleSelonPiece(Piece p_piece, String p_coordonnee) {
        LinkedHashMap<String, String> tourPossible = new LinkedHashMap<>();
        Type type = p_piece.getType();

        ArrayList<String> mouvementsBasique = p_piece.deplacementsPossiblesSelonCoordonnee(p_coordonnee);

        if (type == Type.ROI) {
            for (String coordonne : mouvementsBasique) {
                if (casesVide(p_coordonnee, coordonne)) {
                    tourPossible.put(p_coordonnee, coordonne);
                }
            }

            if (p_coordonnee.equals("e1") || p_coordonnee.equals("e8")) {
                if (deplacementGrandRoqueValide(p_coordonnee, "a" + p_coordonnee.charAt(1))) {
                    tourPossible.put(p_coordonnee, "a" + p_coordonnee.charAt(1));
                }

                if (deplacementPetitRoqueValide(p_coordonnee, "h" + p_coordonnee.charAt(1))) {
                    tourPossible.put(p_coordonnee, "h" + p_coordonnee.charAt(1));
                }
            }

            return tourPossible;
        } else if (type == Type.TOUR) {
            for (String coordonne : mouvementsBasique) {
                if (casesVide(p_coordonnee, coordonne)) {
                    tourPossible.put(p_coordonnee, coordonne);
                }
            }

            if (p_coordonnee.equals("h1") || p_coordonnee.equals("h8")) {
                if (deplacementPetitRoqueValide(p_coordonnee, "e" + p_coordonnee.charAt(1))) {
                    tourPossible.put(p_coordonnee, "e" + p_coordonnee.charAt(1));
                }
            } else if (p_coordonnee.equals("a1") || p_coordonnee.equals("a8")) {
                if (deplacementGrandRoqueValide(p_coordonnee, "e" + p_coordonnee.charAt(1))) {
                    tourPossible.put(p_coordonnee, "e" + p_coordonnee.charAt(1));
                }
            }

            return tourPossible;
        } else if (type == Type.PION) {
            for (String coordonne : mouvementsBasique) {
                if (casesVide(p_coordonnee, coordonne)) {
                    tourPossible.put(p_coordonnee, coordonne);
                }
            }

            tourPossible.putAll(deplacementSpeciauxPion(p_piece, p_coordonnee));

            return tourPossible;
        } else if (type == Type.CAVALIER) {
            for (String coordonne : mouvementsBasique) {
                tourPossible.put(p_coordonnee, coordonne);
            }
            return tourPossible;
        } else {
            for (String coordonne : mouvementsBasique) {
                if (casesVide(p_coordonnee, coordonne)) {
                    tourPossible.put(p_coordonnee, coordonne);
                }
            }

            return tourPossible;
        }
    }

    /**
     * Méthode qui valide l'échec et mat des noirs
     *
     * @return true lorsque les noirs sont EEM
     */
    public boolean estEchecEtMathNoir() {
        String positionRoiNoir = obtenirCoordonneeRoiSelonCouleur(Couleur.NOIR);

        return estEchec(Couleur.NOIR) &&
                getToursPossibleSelonPiece(getPiece(positionRoiNoir),
                        positionRoiNoir).size() == 0;
    }

    /**
     * Méthode qui valide l'échec et mat des blanc
     *
     * @return true lorsque les blancs sont EEM
     */
    public boolean estEchecEtMathBlanc() {
        String positionRoiBlanc = obtenirCoordonneeRoiSelonCouleur(Couleur.BLANC);

        return estEchec(Couleur.BLANC) &&
                getToursPossibleSelonPiece(getPiece(positionRoiBlanc),
                        positionRoiBlanc).size() == 0;
    }

    /**
     * Méthode qui définit si un joueur est en échec
     *
     * @param p_couleur couleur du joueur
     * @return true lorsque le joueur est en échec
     */
    public boolean estEchec(Couleur p_couleur) {
        Couleur couleurOposer;

        if (p_couleur == Couleur.BLANC) {
            couleurOposer = Couleur.NOIR;
        } else {
            couleurOposer = Couleur.BLANC;
        }

        String coordonneeRoi = obtenirCoordonneeRoiSelonCouleur(p_couleur);

        return getToursPossibleSelonCouleur(couleurOposer).containsValue(coordonneeRoi);
    }

    /**
     * @param p_piece      pièce
     * @param p_coordonnee coordonnée où la pièce est placée
     * @return tourPion
     */
    private LinkedHashMap<String, String> deplacementSpeciauxPion(Piece p_piece, @org.jetbrains.annotations.NotNull String p_coordonnee) {
        LinkedHashMap<String, String> tourPion = new LinkedHashMap<>();

        ArrayList<String> deplacementSpeciaux = new ArrayList<>();
        deplacementSpeciaux.add("" + (char) (p_coordonnee.charAt(0) + 1) + (char) (p_coordonnee.charAt(1) + 1));
        deplacementSpeciaux.add("" + (char) (p_coordonnee.charAt(0) + 1) + (char) (p_coordonnee.charAt(1) - 1));
        deplacementSpeciaux.add("" + (char) (p_coordonnee.charAt(0) - 1) + (char) (p_coordonnee.charAt(1) + 1));
        deplacementSpeciaux.add("" + (char) (p_coordonnee.charAt(0) - 1) + (char) (p_coordonnee.charAt(1) - 1));

        for (String coordonnee : deplacementSpeciaux) {
            if (deplacementValidePion(p_piece, p_coordonnee, coordonnee)) {
                tourPion.put(p_coordonnee, coordonnee);
            }
        }

        return tourPion;
    }

    /**
     * Fait la promotion d'un pion
     *
     * @param p_coordonnee pion à promouvoir
     * @param p_type       vers le type de pièce la promotion à lieu
     */
    public void promotion(String p_coordonnee, Type p_type) {
        Piece piece = getPiece(p_coordonnee);
        Piece nouvellePiece;

        switch (p_type) {
            case FOU:
                nouvellePiece = new Roi(piece.getCouleur());
                nouvellePiece.deplacer();
                break;
            case CAVALIER:
                nouvellePiece = new Roi(piece.getCouleur());
                nouvellePiece.deplacer();
                break;
            case DAME:
                nouvellePiece = new Roi(piece.getCouleur());
                nouvellePiece.deplacer();
                break;
            case TOUR:
                nouvellePiece = new Roi(piece.getCouleur());
                nouvellePiece.deplacer();
                break;
            default:
                throw new IllegalArgumentException("Le code ne devrait pas aller ici ou " +
                        "ne devrait pas etre un roi!!!");
        }

        m_echiquier.remove(p_coordonnee);
        m_echiquier.put(p_coordonnee, nouvellePiece);
    }

    /**
     * Vérifie si un pion est à la bonne position pour avoir une promotion
     *
     * @param p_coordonneeDebut coordonnee de debut
     * @param p_coordonneeFin   coordonne de fin
     * @return true si la promotion est possible sinon false
     */
    public boolean estPromotionPossible(String p_coordonneeDebut, String p_coordonneeFin) {
        return (getPiece(p_coordonneeDebut).getCouleur() == Couleur.NOIR && p_coordonneeFin.charAt(1) == '1'
                && getPiece(p_coordonneeDebut).getType() == Type.PION) ||
                (getPiece(p_coordonneeDebut).getCouleur() == Couleur.BLANC && p_coordonneeFin.charAt(1) == '8'
                        && getPiece(p_coordonneeDebut).getType() == Type.PION);
    }

    /**
     * Méthode qui obtient les coordonnées du roi selon sa couleur
     *
     * @param p_couleur couleur du roi qu'on veut obtenir la coordonnée
     * @return coordonnée en string du roi à la couleur choisie
     */
    private String obtenirCoordonneeRoiSelonCouleur(Couleur p_couleur) {
        for (Map.Entry<String, Piece> entry : m_echiquier.entrySet()) {
            if (Objects.equals(Type.ROI, entry.getValue().getType()) &&
                    Objects.equals(p_couleur, entry.getValue().getCouleur())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Est pat selon couleur boolean.
     *
     * @param p_couleur the p couleur
     * @return the boolean
     */
    public boolean estPatSelonCouleur(Couleur p_couleur) {
        return getToursPossibleSelonCouleur(p_couleur).size() == 0 && !estEchec(p_couleur);
    }

    /**
     * Regarde si un cas de partie infinie se produit dans se cas c'est ne partie nulle
     *
     * @return true si la partie est nulle, false dans le cas contraire
     */
    public boolean partieNulle() {
        return m_echiquier.size() <= 3;
    }

    /**
     * Vérifie si un déplacement est valide en prenant en compte le reste de Échiquier
     *
     * @param p_piece          pièce à valider le déplacement
     * @param p_coordonneDebut de départ de la piece
     * @param p_coordonneeFin  coordonnée de destination de la piece
     * @return si le déplacement est valide ou non
     */
    private byte deplacementValide(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        Type type = p_piece.getType();

        switch (type) {
            case PION:
                return (byte) (deplacementValidePion(p_piece, p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            case ROI:
                return deplacementValideRoi(p_piece, p_coordonneDebut, p_coordonneeFin);
            case FOU:
                return (byte) (deplacementValideFou(p_piece, p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            case CAVALIER:
                return (byte) (deplacementValideCavalier(p_piece, p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            case DAME:
                return (byte) (deplacementValideDame(p_piece, p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            case TOUR:
                return deplacementValideTour(p_piece, p_coordonneDebut, p_coordonneeFin);
            default:
                throw new IllegalArgumentException("Le code ne devrait pas aller ici!!!");
        }
    }

    /**
     * Méthode qui test si le déplacement est valide sur une tour
     *
     * @param p_piece          la pièce soit la tour
     * @param p_coordonneDebut coordonnée debut
     * @param p_coordonneeFin  coordonnée de fin
     * @return 2 si c'est un roque ou 0 si le déplacement est invalide ou 1 s'il est valide
     */
    private byte deplacementValideTour(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        if (casesVide(p_coordonneDebut, p_coordonneeFin)) {
            if (deplacementGrandRoqueValide(p_coordonneDebut, p_coordonneeFin)
                    && deplacementPetitRoqueValide(p_coordonneDebut, p_coordonneeFin)) {
                return 2;
            } else {
                return (byte) (p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            }
        } else {
            return 0;
        }
    }

    /**
     * Méthode qui test les déplacements valides d'une DAME
     *
     * @param p_piece          pièce soit une dame
     * @param p_coordonneDebut coordonnée de début de tour
     * @param p_coordonneeFin  coordonnée de fin de tour
     * @return vrai si le déplacement est valide pour la DAME
     */
    private boolean deplacementValideDame(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        return casesVide(p_coordonneDebut, p_coordonneeFin) &&
                p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin) &&
                mangerValidation(p_coordonneDebut, p_coordonneeFin) != 'n';
    }

    /**
     * Méthode qui test le déplacement valide d'un cavalier
     *
     * @param p_piece          la pièce soit le cavalier
     * @param p_coordonneDebut coordonnée de debut de tour
     * @param p_coordonneeFin  coordonnée de fin de tour
     * @return vrai lorsque le déplacement est valide pour un cavalier
     */
    private boolean deplacementValideCavalier(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        return p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin);
    }

    /**
     * Méthode qui test un déplacement valide pour un fou
     *
     * @param p_piece          pièce soit un FOU
     * @param p_coordonneDebut coordonnée de debut de tour
     * @param p_coordonneeFin  coordonnée de fin de tour
     * @return vrai lorsque le déplacement est valide pour un fou
     */
    private boolean deplacementValideFou(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        return casesVide(p_coordonneDebut, p_coordonneeFin) &&
                p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin) &&
                mangerValidation(p_coordonneDebut, p_coordonneeFin) != 'n';
    }

    /**
     * Méthode qui test un déplacement valide pour un ROI
     *
     * @param p_piece          pièce soit un ROI
     * @param p_coordonneDebut coordonnée de debut de tour
     * @param p_coordonneeFin  coordonnée de fin de tour
     * @return vrai lorsque le déplacement est valide pour un ROI
     */
    private byte deplacementValideRoi(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        if (casesVide(p_coordonneDebut, p_coordonneeFin)) {
            if (deplacementGrandRoqueValide(p_coordonneDebut, p_coordonneeFin)
                    && deplacementPetitRoqueValide(p_coordonneDebut, p_coordonneeFin)) {
                return 2;
            } else {
                return (byte) (p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            }
        } else {
            return 0;
        }
    }

    /**
     * Valide si une case est vide ou si la piece peut à cette position peu l'être
     *
     * @param p_coordonneeDebut coordonnée de départ
     * @param p_coordonneeFin   coordonnée de fin
     * @return v si elle est vide, m si la pièce est mangeable et n dans le cas contraire
     */
    private char mangerValidation(String p_coordonneeDebut, String p_coordonneeFin) {
        Piece pieceFin = getPiece(p_coordonneeFin);
        Piece pieceDebut = getPiece(p_coordonneeDebut);

        if (pieceFin == null) {
            return 'v';
        } else if (pieceDebut.getCouleur() != pieceFin.getCouleur()) {
            return 'n';
        } else {
            return 'm';
        }
    }

    /**
     * Méthode qui test les déplacements valides pour un PION
     *
     * @param p_piece           pièce soit le pion
     * @param p_coordonneeDebut coordonnée de debut de tour
     * @param p_coordonneeFin   coordonnée de fin de tour
     * @return vrai lorsque le déplacement sera valide pour un pion
     */
    private boolean deplacementValidePion(Piece p_piece, String p_coordonneeDebut, String p_coordonneeFin) {
        char manger = mangerValidation(p_coordonneeDebut, p_coordonneeFin);

        if (manger == 'v') {
            if (estPriseEnPassantValide(p_coordonneeDebut)) {
                return Math.abs(p_coordonneeFin.charAt(0) - p_coordonneeDebut.charAt(0)) == 1 &&
                        Math.abs(p_coordonneeFin.charAt(1) - p_coordonneeDebut.charAt(1)) == 1
                        && getPiece(p_coordonneeFin) == null;
            } else {
                return p_piece.estDeplacementValide(p_coordonneeDebut, p_coordonneeFin);
            }
        } else if (manger == 'n') {
            return false;
        } else {
            return (p_coordonneeDebut.charAt(0) + 1 == p_coordonneeFin.charAt(0) &&
                    p_coordonneeDebut.charAt(1) + 1 == p_coordonneeFin.charAt(1)) ||
                    (p_coordonneeDebut.charAt(0) - 1 == p_coordonneeFin.charAt(0) &&
                            p_coordonneeDebut.charAt(1) - 1 == p_coordonneeFin.charAt(1)) ||
                    (p_coordonneeDebut.charAt(0) - 1 == p_coordonneeFin.charAt(0) &&
                            p_coordonneeDebut.charAt(1) + 1 == p_coordonneeFin.charAt(1)) ||
                    (p_coordonneeDebut.charAt(0) + 1 == p_coordonneeFin.charAt(0) &&
                            p_coordonneeDebut.charAt(1) - 1 == p_coordonneeFin.charAt(1));
        }
    }

    /**
     * Méthode qui va valider si une prise en passant peut être effectuée
     *
     * @param p_coordonneeDebut coordonnée de debut
     * @return vrai lorsque la prise en passant est disponible
     */
    private boolean estPriseEnPassantValide(String p_coordonneeDebut) {
        if (Objects.equals(dernierTourJouer, p_coordonneeDebut)) {
            return estDernierTourJouerPion == 1;
        } else {
            return false;
        }
    }

    /**
     * Méthode qui test si le petit roque est valide
     *
     * @param p_coordonneePiece1 coordonnée de la première pièce
     * @param p_coordonneePiece2 coordonnée de la deuxième pièce
     * @return vrai lorsque le petit roque est valide
     */
    private boolean deplacementPetitRoqueValide(String p_coordonneePiece1, String p_coordonneePiece2) {
        Piece piece1 = getPiece(p_coordonneePiece1);
        Piece piece2 = getPiece(p_coordonneePiece2);

        if (piece1.estDeplacer() || piece2.estDeplacer()) {
            return false;
        }

        if (Math.abs(p_coordonneePiece1.charAt(0) - p_coordonneePiece2.charAt(0)) == 2 &&
                casesVide(p_coordonneePiece1, p_coordonneePiece2)) {
            if (piece1.getCouleur() == piece2.getCouleur()) {
                return (piece1.getType() == Type.ROI && piece2.getType() == Type.TOUR) ||
                        (piece2.getType() == Type.ROI && piece1.getType() == Type.TOUR);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Méthode qui test si le grand roque est valide
     *
     * @param p_coordonneePiece1 coordonnée de la première pièce
     * @param p_coordonneePiece2 coordonnée de la deuxième pièce
     * @return vrai lorque le grand roque est un déplacement possible
     */
    private boolean deplacementGrandRoqueValide(String p_coordonneePiece1, String p_coordonneePiece2) {
        try {
            Piece piece1 = getPiece(p_coordonneePiece1);
            Piece piece2 = getPiece(p_coordonneePiece2);


            if (piece1.estDeplacer() || piece2.estDeplacer()) {
                return false;
            }

            if (Math.abs(p_coordonneePiece1.charAt(0) - p_coordonneePiece2.charAt(0)) == 3 &&
                    casesVide(p_coordonneePiece1, p_coordonneePiece2)) {
                if (piece1.getCouleur() == piece2.getCouleur()) {
                    return (piece1.getType() == Type.ROI && piece2.getType() == Type.TOUR) ||
                            (piece2.getType() == Type.ROI && piece1.getType() == Type.TOUR);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Tente de déplacer une piece si le déplacement n'est pas valide retourne false
     *
     * @param p_coordonneeDebut coordonnée de départ de la piece
     * @param p_coordonneeFin   coordonnée de destination de la piece
     * @return true le déplacement réussit sinon false
     */
    public boolean deplacerPiece(String p_coordonneeDebut, String p_coordonneeFin) {
        Piece piece = getPiece(p_coordonneeDebut);

        byte valide;
        if (piece != null) {
            valide = deplacementValide(piece, p_coordonneeDebut, p_coordonneeFin);
        } else {
            valide = 3;
        }

        if (valide == 1) {
            m_echiquier.remove(p_coordonneeDebut);

            if (getPiece(p_coordonneeFin) != null) {
                m_echiquier.remove(p_coordonneeFin);
            }

            if (piece.getType() == Type.PION) {
                dernierTourJouer = p_coordonneeFin;
                estDernierTourJouerPion = piece.estDeplacer() ? 1 : 0;
            } else {
                dernierTourJouer = p_coordonneeFin;
                estDernierTourJouerPion = 3;
            }

            piece.deplacer();

            ajouterPiece(p_coordonneeFin, piece);

            return true;
        } else if (valide == 2) {
            Piece pieceRoi = getPiece(p_coordonneeDebut);
            Piece pieceTour = getPiece(p_coordonneeFin);

            pieceRoi.deplacer();
            pieceTour.deplacer();

            m_echiquier.remove(p_coordonneeDebut);
            m_echiquier.remove(p_coordonneeFin);

            if (pieceRoi.getType() != Type.ROI) {
                Piece pieceTempon = pieceRoi;
                pieceTour = pieceRoi;
                pieceRoi = pieceTempon;
            }

            if (Math.abs(p_coordonneeDebut.charAt(0) - p_coordonneeFin.charAt(0)) == 2) {
                if (pieceRoi.getCouleur() == Couleur.NOIR) {
                    ajouterPiece("g8", pieceRoi);
                    ajouterPiece("f8", pieceTour);
                } else {
                    ajouterPiece("g1", pieceRoi);
                    ajouterPiece("f1", pieceTour);
                }
            } else {
                if (pieceRoi.getCouleur() == Couleur.NOIR) {
                    ajouterPiece("c8", pieceRoi);
                    ajouterPiece("d8", pieceTour);
                } else {
                    ajouterPiece("c1", pieceRoi);
                    ajouterPiece("d1", pieceTour);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Vérifie si tout les cases entre les deux déplacement, mais ne vérifie pas la derniere case
     *
     * @param p_coordonneeDebut coordonnée de départ de la piece
     * @param p_coordonneeFin   coordonnée de destination de la piece
     * @return true si les cases sont vides sinon false
     */
    private boolean casesVide(String p_coordonneeDebut, String p_coordonneeFin) {

        if (p_coordonneeDebut.equals(p_coordonneeFin)) {
            return false;
        }

        char lettreDebut = p_coordonneeDebut.charAt(0);
        char chiffreDebut = p_coordonneeDebut.charAt(1);

        char lettreFin = p_coordonneeFin.charAt(0);
        char chiffreFin = p_coordonneeFin.charAt(1);

        if (lettreDebut == lettreFin) {
            if (chiffreDebut < chiffreFin) {
                for (char i = chiffreDebut; i < chiffreFin; i++) {
                    if (getPiece("" + lettreDebut + i) != null) {
                        return false;
                    }
                }
            } else {
                for (char i = chiffreDebut; i > chiffreFin; i--) {
                    if (getPiece("" + lettreDebut + i) != null) {
                        return false;
                    }
                }
            }
        } else if (lettreDebut < lettreFin) {
            for (char i = lettreDebut; i < lettreFin; i++) {
                if (chiffreDebut == chiffreFin) {
                    if (getPiece("" + i + chiffreDebut) != null) {
                        return false;
                    }
                } else if (chiffreDebut < chiffreFin) {
                    for (char j = chiffreDebut; j < chiffreFin; j++) {
                        if (getPiece("" + i + j) != null) {
                            return false;
                        }
                    }
                } else {
                    for (char j = chiffreDebut; j > chiffreFin; j--) {
                        if (getPiece("" + i + j) != null) {
                            return false;
                        }
                    }
                }
            }
        } else {
            for (char i = lettreFin; i < lettreDebut; i++) {
                if (chiffreDebut == chiffreFin) {
                    if (getPiece("" + i + chiffreDebut) != null) {
                        return false;
                    }
                } else if (chiffreDebut < chiffreFin) {
                    for (char j = chiffreDebut; j < chiffreFin; j++) {
                        if (getPiece("" + i + j) != null) {
                            return false;
                        }
                    }
                } else {
                    for (char j = chiffreDebut; j > chiffreFin; j--) {
                        if (getPiece("" + i + j) != null) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
