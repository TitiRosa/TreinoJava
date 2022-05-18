package com.company;

import model.entities.AluguelCarro;
import model.entities.Veiculo;
import services.BrasilTaxaService;
import services.RentalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        System.out.println("Enter rental data");
        System.out.println("Car Model: ");
        String carModel = sc.nextLine();
        System.out.println("Pickup (dd/MM/yyyy hh:ss): ");
        Date start = sdf.parse(sc.nextLine());
        System.out.print("Return (dd/MM/yyyy hh:ss): ");
        Date finish = sdf.parse(sc.nextLine());

        AluguelCarro cr = new AluguelCarro(start, finish, new Veiculo(carModel));

        System.out.print("Enter price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Enter price per day: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrasilTaxaService());

        rentalService.processNotaFiscal(cr);

        System.out.println("INVOICE: ");
        System.out.println("Basic payment: " + String.format("%.2f", cr.getNotaFiscal().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f", cr.getNotaFiscal().getTax()));
        System.out.println("Total payment: " + String.format("%.2f", cr.getNotaFiscal().getTotalPayment()));
        sc.close();
    }
}
