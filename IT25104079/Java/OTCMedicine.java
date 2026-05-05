package com.example.medicalstore.medicine;

public class OTCMedicine extends Medicine {

    public OTCMedicine(String medId, String name, String category,
                       double price, int stock) {
        super(medId, name, category, price, stock);
    }

    @Override
    public String getDisplayInfo() {
        return "OTC";
    }
}
