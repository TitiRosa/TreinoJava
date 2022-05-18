package services;

import model.entities.AluguelCarro;
import model.entities.NotaFiscal;

public class RentalService {

    private Double pricePerDay;
    private Double pricePerHour;

    private BrasilTaxaService taxaService;

    public RentalService(Double pricePerDay, Double pricePerHour, BrasilTaxaService taxaService) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxaService = taxaService;
    }

    public void processNotaFiscal (AluguelCarro aluguelCarro){
        long t1 = aluguelCarro.getStart().getTime();
        long t2 = aluguelCarro.getFinish().getTime();
        double hours = (t2 - t1) / 1000 / 60 / 60;

        double basicPayment;
        if (hours <= 12.0){
            basicPayment = Math.ceil(hours) * pricePerHour;
        }
        else {
            basicPayment = Math.ceil(hours) * pricePerDay;
        }

        double tax = taxaService.tax(basicPayment);
        aluguelCarro.setNotaFiscal(new NotaFiscal(basicPayment, tax));
    }
}
