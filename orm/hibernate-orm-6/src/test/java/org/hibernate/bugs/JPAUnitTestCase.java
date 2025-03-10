package org.hibernate.bugs;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@BeforeEach
	void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@AfterEach
	void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	void hhh123Test() throws Exception {

		final var entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		final Child child = new Child(List.of("parent1", "parent2"), List.of("abstractChild1", "abstractChild2"), List.of("child1", "child2"));
		final Container container = new Container(List.of(child));
		child.setContainer(container);
		entityManager.persist(container);
		final var containerId = container.getId();
		entityManager.getTransaction().commit();

		// build entityGraph to find this entity and eagerly fetch its children
		final EntityGraph<Container> entityGraph = entityManager.createEntityGraph(Container.class);
		entityGraph.addSubgraph("parents", Child.class).addAttributeNodes("childList");


		final Map<String,Object> properties = Map.of("jakarta.persistence.fetchgraph", entityGraph);
		final Container result = entityManager.find(Container.class, containerId, properties);
		entityManager.close();

		final var parent = result.getParents().get(0);

		final var resultChild = assertInstanceOf(Child.class, parent);
		assertEquals(resultChild.getChildList().get(0), "child1");
		assertEquals(resultChild.getChildList().get(1), "child2");
	}
}
