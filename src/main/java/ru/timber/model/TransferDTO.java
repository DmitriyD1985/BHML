package ru.timber.model;

import java.util.List;

public class TransferDTO {
    private List<Songs> rows;

    public List<Songs> getRows() {
        return rows;
    }

    public void setRows(List<Songs> rows) {
        this.rows = rows;
    }
}