package com.example.cityfalcon.Models;

import com.example.cityfalcon.Models.SectorForSectorsArticle;

import java.util.ArrayList;

public class SectorsArticle {
    private Boolean success;
    private ArrayList<SectorForSectorsArticle> sectors;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ArrayList<SectorForSectorsArticle> getSectors() {
        return sectors;
    }

    public void setSectors(ArrayList<SectorForSectorsArticle> sectors) {
        this.sectors = sectors;
    }
}
