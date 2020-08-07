package com.example.cityfalcon.Models;

import com.example.cityfalcon.Models.SignalFromSignalsBuySellArticle;

import java.util.ArrayList;

public class SignalsSearchArticle {

   Integer count;
   ArrayList<SignalFromSignalsBuySellArticle> list;

   public Integer getCount() {
      return count;
   }

   public void setCount(Integer count) {
      this.count = count;
   }

   public ArrayList<SignalFromSignalsBuySellArticle> getList() {
      return list;
   }

   public void setList(ArrayList<SignalFromSignalsBuySellArticle> list) {
      this.list = list;
   }
}
