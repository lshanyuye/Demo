package com.mur.model.menuItem;

import com.mur.entity.ITree;
import com.mur.model.Entity;

public class MenuItem  extends Entity implements ITree{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_item.ID
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_item.PID
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    private String pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_item.DISPLAY_NAME
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    private String displayName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_item.TYPE
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_item.URL
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_item.ICO
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    private String icon;
    
    private Boolean checked = Boolean.FALSE;
    
    private String seq;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_item.ID
     *
     * @return the value of menu_item.ID
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_item.ID
     *
     * @param id the value for menu_item.ID
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_item.PID
     *
     * @return the value of menu_item.PID
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public String getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_item.PID
     *
     * @param pid the value for menu_item.PID
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_item.DISPLAY_NAME
     *
     * @return the value of menu_item.DISPLAY_NAME
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_item.DISPLAY_NAME
     *
     * @param displayName the value for menu_item.DISPLAY_NAME
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_item.TYPE
     *
     * @return the value of menu_item.TYPE
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_item.TYPE
     *
     * @param type the value for menu_item.TYPE
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_item.URL
     *
     * @return the value of menu_item.URL
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_item.URL
     *
     * @param url the value for menu_item.URL
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_item.ICO
     *
     * @return the value of menu_item.ICO
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public String getIcon() {
        return icon;
    }

    public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_item.ICO
     *
     * @param ico the value for menu_item.ICO
     *
     * @mbggenerated Mon Jan 09 16:05:47 CST 2017
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
	public String getText() {
		return getDisplayName();
	}

	@Override
	public boolean isChecked() {
		return checked;
	}

	@Override
	public String getOpenClosed() {
		return null;
	}
}