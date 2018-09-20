package com.github.binarywang.profitsharing.bean.order;

import lombok.Data;

@Data
public class ProfitSharingReceiver {

  private String type;
  private String account;
  private int amount;
  private String description;
  private String name;

}
