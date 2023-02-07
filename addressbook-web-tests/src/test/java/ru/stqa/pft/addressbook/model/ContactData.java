package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {

  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstName;
  @Expose
  private String lastName;
  @XStreamOmitField
  private File photo;
  @Expose
  private String homePhone;
  @Expose
  private String mobilePhone;
  @Expose
  private String workPhone;
  @Expose
  private String phone2;
  @Expose
  private String address;
  @XStreamOmitField
  private String allPhones;
  @Expose
  private String email;
  @Expose
  private String email2;
  @Expose
  private String email3;
  @XStreamOmitField
  private String allEmail;
  @XStreamOmitField
  private String group;
  @XStreamOmitField
  private int groupId = 0;

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public File getPhoto() {
    return photo;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getPhone2() {
    return phone2;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmail() {
    return allEmail;
  }

  public String getGroup() {
    return group;
  }

  public int getGroupId() {
    return groupId;
  }


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withPhone2(String phone2) {
    this.phone2 = phone2;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmail(String allEmail) {
    this.allEmail = allEmail;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withGroupId(int groupId) {
    this.groupId = groupId;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", allEmail='" + allEmail + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;

    if ( (firstName != null && !firstName.equals("") ) && (that.firstName == null || that.firstName.equals(""))) return false;
    if ( (that.firstName != null && !that.firstName.equals("") ) && (firstName == null || firstName.equals(""))) return false;
    if (firstName != null && that.firstName != null && !Objects.equals(firstName, that.firstName)) return false;

    if ( (lastName != null && !lastName.equals("") ) && (that.lastName == null || that.lastName.equals(""))) return false;
    if ( (that.lastName != null && !that.lastName.equals("") ) && (lastName == null || lastName.equals(""))) return false;
    if (lastName != null && that.lastName != null && !Objects.equals(lastName, that.lastName)) return false;

    if ( (address != null && !address.equals("") ) && (that.address == null || that.address.equals(""))) return false;
    if ( (that.address != null && !that.address.equals("") ) && (address == null || address.equals(""))) return false;
    if (address != null && that.address != null && !Objects.equals(address, that.address)) return false;

    if ( (allPhones != null && !allPhones.equals("") ) && (that.allPhones == null || that.allPhones.equals(""))) return false;
    if ( (that.allPhones != null && !that.allPhones.equals("") ) && (allPhones == null || allPhones.equals(""))) return false;
    if (allPhones != null && that.allPhones != null && !Objects.equals(allPhones, that.allPhones)) return false;

    if ( (allEmail != null && !allEmail.equals("") ) && (that.allEmail == null || that.allEmail.equals(""))) return false;
    if ( (that.allEmail != null && !that.allEmail.equals("") ) && (allEmail == null || allEmail.equals(""))) return false;
    if (allEmail != null && that.allEmail != null && !Objects.equals(allEmail, that.allEmail)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
    result = 31 * result + (allEmail != null ? allEmail.hashCode() : 0);
    return result;
  }
}
