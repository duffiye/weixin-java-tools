package com.github.binarywang.profitsharing.bean.order;

public class ProfitSharingReceiver {

  private String type;
  private String account;
  private int amount;
  private String description;

  public String getType() {
    return type;
  }

  public ProfitSharingReceiver setType(String type) {
    this.type = type;
    return this;
  }

  public String getAccount() {
    return account;
  }

  public ProfitSharingReceiver setAccount(String account) {
    this.account = account;
    return this;
  }

  public int getAmount() {
    return amount;
  }

  public ProfitSharingReceiver setAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ProfitSharingReceiver setDescription(String description) {
    this.description = description;
    return this;
  }
}
