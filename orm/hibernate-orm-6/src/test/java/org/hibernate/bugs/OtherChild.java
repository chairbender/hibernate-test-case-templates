package org.hibernate.bugs;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Entity
@DiscriminatorValue("other")
public class OtherChild extends Parent {

  @ElementCollection(targetClass = String.class)
  @CollectionTable(name = "other_child_value",
      joinColumns = {@JoinColumn(name = "parent_id", referencedColumnName = "id")})
  private List<String> otherChildList;

  public OtherChild(final List<String> parentList, final List<String> otherChildList) {
    super(parentList);
    this.otherChildList = otherChildList;
  }
}
