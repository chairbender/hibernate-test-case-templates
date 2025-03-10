package org.hibernate.bugs;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Entity
public abstract class AbstractChild extends Parent {

  @ElementCollection(targetClass = String.class)
  @CollectionTable(name = "abstract_child_value",
      joinColumns = {@JoinColumn(name = "parent_id", referencedColumnName = "id")})
  private List<String> abstractChildList;


  public AbstractChild(final List<String> parentList, final List<String> abstractChildList) {
    super(parentList);
    this.abstractChildList = abstractChildList;
  }
}
