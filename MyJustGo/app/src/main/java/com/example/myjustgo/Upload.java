package com.example.myjustgo;

public class Upload {

    private  String mDiaDiem;
    private  String mName;
    private String mTime;
    private String mImageUrl;

    public Upload()
    {

    }
    public Upload(String dd,String name,String time,String imageUrl)
    {
        if(name.trim().equals("") ){
        mName="No Name";

    }
        if(dd.trim().equals("") ){
          mDiaDiem = "No ";
        }
        if(time.trim().equals(""))
        {
            mTime = "no time";
        }
        mDiaDiem =dd;
        mName = name;
        mTime = time;
        mImageUrl = imageUrl;
    }
    public  String getName()
    {
        return mName;

    }
    public void setName(String name)
    {
        mName = name;
    }
    public String getDiaDiem()
    {
        return mDiaDiem;
    }
    public void setDiaDiem(String address)
    {
       mDiaDiem = address;
    }
    public String getmImageUrl()
    {
        return mImageUrl;
    }
    public void setmImageUrl(String imageUrl)
    {
        mImageUrl = imageUrl;
    }
    public String getTime()
    {
        return mTime;
    }
    public void setTime(String time)
    {
        mTime = time;
    }

}
