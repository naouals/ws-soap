package org.example.clientsoapjava;


import proxy.BanqueService;
import proxy.BanqueWS;
import proxy.Compte;


public class ClientSoapJavaApplication {

    public static void main(String[] args) {
        BanqueService proxy=new BanqueWS().getBanqueServicePort();
        System.out.println(proxy.conservationEuroToDH(40));
        Compte compte=proxy.getCompte(4);
        System.out.println("------------------------");
        System.out.println(compte.getCode());
        System.out.println(compte.getSolde());
        System.out.println(compte.getDate());

        proxy.getComptes().forEach(cb->{
            System.out.println("------------------------");
            System.out.println(cb.getCode());
            System.out.println(cb.getSolde());
            System.out.println(cb.getDate());
        });
        }
    }


