package com.kits.project.DTOs;

public class ReportDTO {
    private float noTotal;
    private float total;
    private float avgPerDay;
    private float single;
    private float totalSingle;
    private float daily;
    private float totalDaily;
    private float monthly;
    private float totalMonthly;
    private float yearly;
    private float totalYearly;

    public ReportDTO() {}

    public ReportDTO(float noTotal, float total, float avgPerDay, float single, float totalSingle, float daily, float totalDaily, float monthly, float totalMonthly, float yearly, float totalYearly) {
        this.noTotal = noTotal;
        this.total = total;
        this.avgPerDay = avgPerDay;
        this.single = single;
        this.totalSingle = totalSingle;
        this.daily = daily;
        this.totalDaily = totalDaily;
        this.monthly = monthly;
        this.totalMonthly = totalMonthly;
        this.yearly = yearly;
        this.totalYearly = totalYearly;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getAvgPerDay() {
        return avgPerDay;
    }

    public void setAvgPerDay(float avgPerDay) {
        this.avgPerDay = avgPerDay;
    }

    public float getSingle() {
        return single;
    }

    public void setSingle(float single) {
        this.single = single;
    }

    public float getTotalSingle() {
        return totalSingle;
    }

    public void setTotalSingle(float totalSingle) {
        this.totalSingle = totalSingle;
    }

    public float getDaily() {
        return daily;
    }

    public void setDaily(float daily) {
        this.daily = daily;
    }

    public float getTotalDaily() {
        return totalDaily;
    }

    public void setTotalDaily(float totalDaily) {
        this.totalDaily = totalDaily;
    }

    public float getMonthly() {
        return monthly;
    }

    public void setMonthly(float monthly) {
        this.monthly = monthly;
    }

    public float getTotalMonthly() {
        return totalMonthly;
    }

    public void setTotalMonthly(float totalMonthly) {
        this.totalMonthly = totalMonthly;
    }

    public float getYearly() {
        return yearly;
    }

    public void setYearly(float yearly) {
        this.yearly = yearly;
    }

    public float getTotalYearly() {
        return totalYearly;
    }

    public void setTotalYearly(float totalYearly) {
        this.totalYearly = totalYearly;
    }

    public float getNoTotal() {
        return noTotal;
    }

    public void setNoTotal(float noTotal) {
        this.noTotal = noTotal;
    }
}
