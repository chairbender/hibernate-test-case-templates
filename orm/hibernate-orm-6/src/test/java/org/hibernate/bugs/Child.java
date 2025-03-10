package org.hibernate.bugs;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Entity
@DiscriminatorValue("child")
public class Child extends AbstractChild {

  @ElementCollection(targetClass = String.class)
  @CollectionTable(name = "child_value",
      joinColumns = {@JoinColumn(name = "parent_id", referencedColumnName = "id")})
  private List<String> childList;


  public Child(final List<String> parentList, final List<String> abstractChildList,
               final List<String> childList) {
    super(parentList, abstractChildList);
    this.childList = childList;
  }

  public List<String> getChildList() {
    return childList;
  }
}
