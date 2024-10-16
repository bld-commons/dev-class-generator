/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.attributes.StandardImportType.java
 * 
 */
package com.bld.commons.classes.attributes;

// TODO: Auto-generated Javadoc
/**
 * The Enum StandardImportType.
 */
public enum StandardImportType {

	/** The Hash map. */
	HashMap("java.util.HashMap"),

	/** The Timestamp. */
	Timestamp("java.sql.Timestamp"),

	/** The Big integer. */
	BigInteger("java.math.BigInteger"),

	/** The Jpa service. */
	JpaService("com.bld.commons.service.JpaService"),

	/** The Jpa service impl. */
	JpaServiceImpl("com.bld.commons.service.JpaServiceImpl"),

	/** The Query jpql. */
	QueryJpql("com.bld.commons.service.QueryJpql"),

	/** The Big decimal. */
	BigDecimal("java.math.BigDecimal"),

	/** The Date. */
	Date("java.util.Date"),

	/** The Calendar. */
	Calendar("java.util.Calendar"),

	/** The Base jpa repository. */
	BaseJpaRepository("com.bld.commons.repository.BaseJpaRepository"),
	
	/** The Base jpa service. */
	BaseJpaService("com.bld.commons.service.BaseJpaService"),

	/** The List. */
	List("java.util.List"),

	/** The Set. */
	Set("java.util.Set"),

	/** The Map. */
	Map("java.util.Map"),

	/** The Array list. */
	ArrayList("java.util.ArrayList"),

	/** The Hash set. */
	HashSet("java.util.HashSet"),

	/** The Serializable. */
	Serializable("java.io.Serializable"),

	/** The Entity manager. */
	EntityManager("jakarta.persistence.EntityManager"),

	/** The Named parameter jdbc template. */
	NamedParameterJdbcTemplate("org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate"),

	/** The Jpa repository. */
	JpaRepository("org.springframework.data.jpa.repository.JpaRepository"),

	/** The Annotation repository. */
	AnnotationRepository("org.springframework.stereotype.Repository"),

	/** The Annotation service. */
	AnnotationService("org.springframework.stereotype.Service"),

	/** The Annotation component. */
	AnnotationComponent("org.springframework.stereotype.Component"),

	/** The Annotation not null. */
	AnnotationNotNull("jakarta.validation.constraints.NotNull"),

	/** The Annotation data. */
	AnnotationData("lombok.Data"),
	/** The Annotation size. */
	AnnotationSize("jakarta.validation.constraints.Size"),

	/** The Annotation transactional. */
	AnnotationTransactional("org.springframework.transaction.annotation.Transactional"),

	/** The Annotation autowired. */
	AnnotationAutowired("org.springframework.beans.factory.annotation.Autowired"),

	/** The Annotation persistence context. */
	AnnotationPersistenceContext("jakarta.persistence.PersistenceContext"),

	/** The Annotation created by. */
	AnnotationCreatedBy("org.springframework.data.annotation.CreatedBy"),

	/** The Annotation created date. */
	AnnotationCreatedDate("org.springframework.data.annotation.CreatedDate"),

	/** The Annotation last modified by. */
	AnnotationLastModifiedBy("org.springframework.data.annotation.LastModifiedBy"),

	/** The Annotation last modified date. */
	AnnotationLastModifiedDate("org.springframework.data.annotation.LastModifiedDate"),

	/** The Annotation column. */
	AnnotationColumn("jakarta.persistence.Column"),

	/** The Annotation version. */
	AnnotationVersion("jakarta.persistence.Version"),

	/** The Annotation query builder. */
	AnnotationQueryBuilder("com.bld.commons.processor.annotations.QueryBuilder"),

	/** The Annotation join builder. */
	AnnotationJoinBuilder("com.bld.commons.processor.annotations.JoinBuilder"),

	/** The Annotation condition builder. */
	AnnotationConditionBuilder("bld.commons.processor.annotations.ConditionBuilder"),

	;

	/** The standard import. */
	private String standardImport;

	/**
	 * Instantiates a new standard import type.
	 *
	 * @param standardImport the standard import
	 */
	private StandardImportType(String standardImport) {
		this.standardImport = standardImport;
	}

	/**
	 * Gets the standard import.
	 *
	 * @return the standard import
	 */
	public String getStandardImport() {
		return standardImport;
	}

	/**
	 * Contains.
	 *
	 * @param className the class name
	 * @return true, if successful
	 */
	public static boolean contains(String className) {
		for (StandardImportType standardImportType : StandardImportType.values())
			if (standardImportType.name().equals(className))
				return true;
		return false;
	}

}
