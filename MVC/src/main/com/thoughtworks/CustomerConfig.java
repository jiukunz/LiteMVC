package com.thoughtworks;

public abstract class CustomerConfig implements ICustomerConfig{
    private String pkgName;

    @Override
    public abstract void config();

    @Override
    public void setPackageName(String pkgName) {
        this.pkgName = pkgName;
    }

    @Override
    public String getPackageName() {
        return this.pkgName;
    }
}
