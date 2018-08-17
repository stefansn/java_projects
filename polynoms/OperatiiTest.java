import static org.junit.jupiter.api.Assertions.*;

class OperatiiTest {

    @org.junit.jupiter.api.Test
    void adunare() {
        Operatii op = new Operatii();
        Polinom pol1 = new Polinom("2x^3+2x+1");
        Polinom pol2 = new Polinom("2x+1");
        Polinom rez = new Polinom();
        rez = op.adunare(pol1,pol2);

        assertEquals("+2x^3+4x^1+2x^0",rez.showPol(rez));
    }

    @org.junit.jupiter.api.Test
    void scadere() {
        Operatii op = new Operatii();
        Polinom pol1 = new Polinom("2x^3+2x+1");
        Polinom pol2 = new Polinom("2x+1");
        Polinom rez = new Polinom();
        rez = op.scadere(pol1,pol2);

        assertEquals("+2x^3",rez.showPol(rez));
    }

    @org.junit.jupiter.api.Test
    void inmultire() {
        Operatii op = new Operatii();
        Polinom pol1 = new Polinom("2x^2+2");
        Polinom pol2 = new Polinom("2x");
        Polinom rez = new Polinom();
        rez = op.inmultire(pol1,pol2);

        assertEquals("+4x^3+4x^1",rez.showPol(rez));
    }

    @org.junit.jupiter.api.Test
    void derivare() {
        Operatii op = new Operatii();
        Polinom pol1 = new Polinom("2x^2+2");

        Polinom rez = new Polinom();
        rez = op.derivare(pol1);

        assertEquals("+4x^1",rez.showPol(rez));
    }
}