package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {

  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  private String firstName;
  private String lastName;
  @XStreamOmitField
  private File photo;
  private String homePhone;
  private String mobilePhone;

  private String workPhone;
  private String phone2;
  private String address;
  @XStreamOmitField
  private String allPhones;
  private String email;
  private String email2;
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
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (!Objects.equals(firstName, that.firstName)) return false;
    return Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}
