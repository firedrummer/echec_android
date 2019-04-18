package com.example.echec_android;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Classe qui effectue le test complet de tous les tests
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestEchiquier.class,
        TestRoi.class,
        TestReine.class,
        TestPion.class,
        TestFou.class,
        TestCavalier.class,
        TestTour.class
})

public class TestComplet {
}