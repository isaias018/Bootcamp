package tareadia3;
public final class Reloj {
    private int horas;   // Rango: 0 - 23
    private int minutos; // Rango: 0 - 59
    private int segundos;// Rango: 0 - 59

    public Reloj() {
        this.horas = 12;
        this.minutos = 0;
        this.segundos = 0;
    }

    public Reloj(int horas, int minutos, int segundos) {
        setHoras(horas);
        setMinutos(minutos);
        setSegundos(segundos);
    }

    public Reloj(int totalSegundos) {
        setReloj(totalSegundos);
    }

    public void setReloj(int totalSegundos) {
        totalSegundos = totalSegundos % 86400; // segundos en 24h
        this.horas = (totalSegundos / 3600) % 24;
        this.minutos = (totalSegundos % 3600) / 60;
        this.segundos = totalSegundos % 60;
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setHoras(int horas) {
        if (horas >= 0 && horas < 24)
            this.horas = horas;
    }

    public void setMinutos(int minutos) {
        if (minutos >= 0 && minutos < 60)
            this.minutos = minutos;
    }

    public void setSegundos(int segundos) {
        if (segundos >= 0 && segundos < 60)
            this.segundos = segundos;
    }

    public void tick() {
        segundos++;
        if (segundos == 60) {
            segundos = 0;
            minutos++;
            if (minutos == 60) {
                minutos = 0;
                horas = (horas + 1) % 24;
            }
        }
    }

    public void tickDecrement() {
        segundos--;
        if (segundos < 0) {
            segundos = 59;
            minutos--;
            if (minutos < 0) {
                minutos = 59;
                horas = (horas - 1 + 24) % 24;
            }
        }
    }

    public void addReloj(Reloj otro) {
        int total = this.toSegundos() + otro.toSegundos();
        setReloj(total);
    }

    public Reloj restaReloj(Reloj otro) {
        int diferencia = this.toSegundos() - otro.toSegundos();
        if (diferencia < 0)
            diferencia += 86400; // ajusta si es negativo
        return new Reloj(diferencia);
    }

    
    private int toSegundos() {
        return horas * 3600 + minutos * 60 + segundos;
    }

    
 @Override
    public String toString() {
        return String.format("[%02d:%02d:%02d]", horas, minutos, segundos);
    }
}

  
    
