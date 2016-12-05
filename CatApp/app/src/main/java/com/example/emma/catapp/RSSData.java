package com.example.emma.catapp;

import java.io.Serializable;

/**
 * Created by Emma on 04/12/2016.
 */
public class RSSData implements Serializable
{
    private String itemTitle;
    private String itemLink;
    private String itemDesc;

    public String getItemTitle()
    {
        return this.itemTitle;
    }

    public void setItemTitle(String sItemTitle)
    {
        this.itemTitle = sItemTitle;
    }

    public String getItemDesc()
    {
        return this.itemDesc;
    }

    public void setItemDesc(String sItemDesc)
    {
        this.itemDesc = sItemDesc;
    }

    public String getItemLink()
    {
        return this.itemLink;
    }

    public void setItemLink(String sItemLink)
    {
        this.itemLink = sItemLink;
    }


    public RSSData()
    {
        this.itemTitle = "";
        this.itemDesc = "";
        this.itemLink = "";
    }

    @Override
    public String toString() {
        String catData;
        catData = "RSSData [itemTitle=" + itemTitle;
        catData = ", itemDesc=" + itemDesc;
        catData = ", itemLink=" + itemLink+"]";
        return catData;
    }
}
