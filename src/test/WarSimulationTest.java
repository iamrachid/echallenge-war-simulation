package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.sqli.nespresso.war.War;
import com.sqli.nespresso.war.WarBuilder;
import com.sqli.nespresso.war.kingdoms.Kingdom;
import com.sqli.nespresso.war.kingdoms.KingdomBuilder;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class WarSimulationTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void showKingdom() {
        Kingdom Kingdom = new KingdomBuilder()
                .addKing("Youness")
                .addCountry("France", "20", "100", "50", "200", "100", "100")
                .addCountry("Spain", "30", "200", "40", "300")
                .build();
        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>,S:<Sc1:30-200,Sc2:40-300>|", Kingdom.report());
    }

    @Test
    public void showKingdomPower() {
        Kingdom Kingdom = new KingdomBuilder()
                .addKing("Youness")
                .addCountry("France", "20", "100", "50", "200", "100", "100")
                .addCountry("Spain", "30", "200", "40", "300")
                .build();
        assertEquals(240, Kingdom.currentPower(), 0);
    }

    @Test
    public void aKingdomHasSoldiersOnEdges() {
        Kingdom Kingdom1 = new KingdomBuilder()
                .addKing("Youness")
                .addCountry("France", "20", "100", "50", "200", "100", "100")
                .addCountry("Spain", "30", "200", "40", "300")
                .addSoldiersOnEdges("500")
                .build();
        Kingdom Kingdom2 = new KingdomBuilder()
                .addKing("Amine")
                .addCountry("USA", "500", "1000", "400", "500", "200", "300", "2000", "300")
                .build();
        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-500,S:<Sc1:30-200,Sc2:40-300>-500|", Kingdom1.report());
        assertEquals("Amine:|U:<Uc1:500-1000,Uc2:400-500,Uc3:200-300,Uc4:2000-300>|", Kingdom2.report());
        assertEquals(240, Kingdom1.currentPower());
        assertEquals(3100, Kingdom2.currentPower());

    }

    @Test
    public void aKingdomCanPrepareAnAttacksAnOther() {
        Kingdom Kingdom1 = new KingdomBuilder()
                .addKing("Youness")
                .addCountry("France", "20", "100", "50", "200", "100", "100")
                .addCountry("Spain", "30", "200", "40", "300")
                .addSoldiersOnEdges("500")
                .build();
        Kingdom Kingdom2 = new KingdomBuilder()
                .addKing("Amine")
                .addCountry("USA", "30", "200", "40", "300")
                .addSoldiersOnEdges("200")
                .build();
        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-500,S:<Sc1:30-200,Sc2:40-300>-500|", Kingdom1.report());
        assertEquals("Amine:|U:<Uc1:30-200,Uc2:40-300>-200|", Kingdom2.report());
        assertEquals(240, Kingdom1.currentPower());
        assertEquals(70, Kingdom2.currentPower());

        War war = new WarBuilder()
                .addKingdom(Kingdom1)
                .addKingdom(Kingdom2)
                .addMap("France:100:Spain,France:1000:USA,Spain:1500:USA")
                .build();
        war.prepareAttack();
        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-535,S:<Sc1:15-200,Sc2:20-300>-500|", Kingdom1.report());
        assertEquals("Amine:|U:<Uc1:30-200,Uc2:40-300>-200|", Kingdom2.report());
        assertEquals(205, Kingdom1.currentPower());
        assertEquals(70, Kingdom2.currentPower());
    }

    @Test
    public void aKingdomCanAttackAnother() {
        Kingdom Kingdom1 = new KingdomBuilder()
                .addKing("Youness")
                .addCountry("France", "20", "100", "50", "200", "100", "100")
                .addCountry("Spain", "30", "200", "40", "300")
                .addSoldiersOnEdges("500")
                .build();
        Kingdom Kingdom2 = new KingdomBuilder()
                .addKing("Amine")
                .addCountry("USA", "30", "200", "40", "300")
                .addSoldiersOnEdges("200")
                .build();
        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-500,S:<Sc1:30-200,Sc2:40-300>-500|", Kingdom1.report());
        assertEquals("Amine:|U:<Uc1:30-200,Uc2:40-300>-200|", Kingdom2.report());
        assertEquals(240, Kingdom1.currentPower());
        assertEquals(70, Kingdom2.currentPower());

        War war = new WarBuilder()
                .addKingdom(Kingdom1)
                .addKingdom(Kingdom2)
                .addMap("France:100:Spain,France:1000:USA,Spain:1500:USA")
                .build();
        war.prepareAttack();
        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-535,S:<Sc1:15-200,Sc2:20-300>-500|", Kingdom1.report());
        assertEquals("Amine:|U:<Uc1:30-200,Uc2:40-300>-200|", Kingdom2.report());
        assertEquals(205, Kingdom1.currentPower());
        assertEquals(70, Kingdom2.currentPower());
        war.start();
        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-335,S:<Sc1:15-200,Sc2:20-300>-500|", Kingdom1.report());
        assertEquals("Amine:|U:<Uc1:30-200,Uc2:40-300>|", Kingdom2.report());
        assertEquals(205, Kingdom1.currentPower());
        assertEquals(70, Kingdom2.currentPower());

    }

}