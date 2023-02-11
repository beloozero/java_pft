package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstname;
  @Expose
  private String lastname;
  @XStreamOmitField
  @Type(type = "text")
  private String photo;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;
  @Expose
  @Type(type = "text")
  private String phone2;
  @Expose
  @Type(type = "text")
  private String address;
  @XStreamOmitField
  @Transient
  private String allPhones;
  @Expose
  @Type(type = "text")
  private String email;
  @Expose
  @Type(type = "text")
  private String email2;
  @Expose
  @Type(type = "text")
  private String email3;
  @XStreamOmitField
  @Transient
  private String allEmail;
  @XStreamOmitField
  @Transient
  private String group;
  @XStreamOmitField
  @Transient
  private int groupId = 0;

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstname;
  }

  public String getLastName() {
    return lastname;
  }

  public File getPhoto() {
    if (photo != null) {
      return new File(photo);
    } else {
      return null;
    }
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
    this.firstname = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastname = lastName;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
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
            ", firstName='" + firstname + '\'' +
            ", lastName='" + lastname + '\'' +
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

    if ( (firstname != null && !firstname.equals("") ) && (that.firstname == null || that.firstname.equals(""))) return false;
    if ( (that.firstname != null && !that.firstname.equals("") ) && (firstname == null || firstname.equals(""))) return false;
    if (firstname != null && that.firstname != null && !Objects.equals(firstname, that.firstname)) return false;

    if ( (lastname != null && !lastname.equals("") ) && (that.lastname == null || that.lastname.equals(""))) return false;
    if ( (that.lastname != null && !that.lastname.equals("") ) && (lastname == null || lastname.equals(""))) return false;
    if (lastname != null && that.lastname != null && !Objects.equals(lastname, that.lastname)) return false;

    if ( (address != null && !address.equals("") ) && (that.address == null || that.address.equals(""))) return false;
    if ( (that.address != null && !that.address.equals("") ) && (address == null || address.equals(""))) return false;
    if (address != null && that.address != null && !Objects.equals(address, that.address)) return false;

    if ( (homePhone != null && !homePhone.equals("") ) && (that.homePhone == null || that.homePhone.equals(""))) return false;
    if ( (that.homePhone != null && !that.homePhone.equals("") ) && (homePhone == null || homePhone.equals(""))) return false;
    if (homePhone != null && that.homePhone != null && !Objects.equals(homePhone, that.homePhone)) return false;

    if ( (mobilePhone != null && !mobilePhone.equals("") ) && (that.mobilePhone == null || that.mobilePhone.equals(""))) return false;
    if ( (that.mobilePhone != null && !that.mobilePhone.equals("") ) && (mobilePhone == null || mobilePhone.equals(""))) return false;
    if (mobilePhone != null && that.mobilePhone != null && !Objects.equals(mobilePhone, that.mobilePhone)) return false;

    if ( (workPhone != null && !workPhone.equals("") ) && (that.workPhone == null || that.workPhone.equals(""))) return false;
    if ( (that.workPhone != null && !that.workPhone.equals("") ) && (workPhone == null || workPhone.equals(""))) return false;
    if (workPhone != null && that.workPhone != null && !Objects.equals(workPhone, that.workPhone)) return false;

    if ( (phone2 != null && !phone2.equals("") ) && (that.phone2 == null || that.phone2.equals(""))) return false;
    if ( (that.phone2 != null && !that.phone2.equals("") ) && (phone2 == null || phone2.equals(""))) return false;
    if (phone2 != null && that.phone2 != null && !Objects.equals(phone2, that.phone2)) return false;

    if ( (email != null && !email.equals("") ) && (that.email == null || that.email.equals(""))) return false;
    if ( (that.email != null && !that.email.equals("") ) && (email == null || email.equals(""))) return false;
    if (email != null && that.email != null && !Objects.equals(email, that.email)) return false;

    if ( (email2 != null && !email2.equals("") ) && (that.email2 == null || that.email2.equals(""))) return false;
    if ( (that.email2 != null && !that.email2.equals("") ) && (email2 == null || email2.equals(""))) return false;
    if (email2 != null && that.email2 != null && !Objects.equals(email2, that.email2)) return false;

    if ( (email3 != null && !email3.equals("") ) && (that.email3 == null || that.email3.equals(""))) return false;
    if ( (that.email3 != null && !that.email3.equals("") ) && (email3 == null || email3.equals(""))) return false;
    if (email3 != null && that.email3 != null && !Objects.equals(email3, that.email3)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    return result;
  }
}
