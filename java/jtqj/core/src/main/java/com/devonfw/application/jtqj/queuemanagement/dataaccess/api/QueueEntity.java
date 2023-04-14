package com.devonfw.application.jtqj.queuemanagement.dataaccess.api;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.devonfw.application.jtqj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.jtqj.queuemanagement.common.api.Queue;

@Entity
@Table(name = "DailyQueue")
public class QueueEntity extends ApplicationPersistenceEntity implements Queue {

  private String name;

  private String logo;

  private String currentNumber;

  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp attentionTime;

  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp minAttentionTime;

  private Boolean active;

  private int customers;

  private static final long serialVersionUID = 1L;

  /**
   * @return the name
   */
  @Override
  public String getName() {

    return this.name;
  }

  /**
   * @param name the name to set
   */
  @Override
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return the logo
   */
  @Override
  public String getLogo() {

    return this.logo;
  }

  /**
   * @param logo the logo to set
   */
  @Override
  public void setLogo(String logo) {

    this.logo = logo;
  }

  /**
   * @return the currentNumber
   */
  @Override
  public String getCurrentNumber() {

    return this.currentNumber;
  }

  /**
   * @param currentNumber the currentNumber to set
   */
  @Override
  public void setCurrentNumber(String currentNumber) {

    this.currentNumber = currentNumber;
  }

  /**
   * @return the attentionTime
   */
  @Override
  public Timestamp getAttentionTime() {

    return this.attentionTime;
  }

  /**
   * @param attentionTime the attentionTime to set
   */
  @Override
  public void setAttentionTime(Timestamp attentionTime) {

    this.attentionTime = attentionTime;
  }

  /**
   * @return the minAttentionTime
   */
  @Override
  public Timestamp getMinAttentionTime() {

    return this.minAttentionTime;
  }

  /**
   * @param minAttentionTime the minAttentionTime to set
   */
  @Override
  public void setMinAttentionTime(Timestamp minAttentionTime) {

    this.minAttentionTime = minAttentionTime;
  }

  /**
   * @return the active
   */
  @Override
  public Boolean getActive() {

    return this.active;
  }

  /**
   * @param active the active to set
   */
  @Override
  public void setActive(Boolean active) {

    this.active = active;
  }

  /**
   * @return the customers
   */
  @Override
  public int getCustomers() {

    return this.customers;
  }

  /**
   * @param customers the customers to set
   */
  @Override
  public void setCustomers(int customers) {

    this.customers = customers;
  }
}