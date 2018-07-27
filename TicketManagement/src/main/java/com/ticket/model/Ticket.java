package com.ticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ticket")
public class Ticket {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="ticket_id")
	    private Integer id;

	    @Column(nullable=false)
	    private String title;

	    @Column(nullable=false)
	    private String description;

	    @Column(nullable = false)
	    private Integer aplication_id;

	    @Column(nullable = false)
	    private Long user_id;
	    
	    @Transient
	    private String user_name;
	    
	    @Transient
	    private String application_name;
	    
	    

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getApplication_name() {
			return application_name;
		}

		public void setApplication_name(String application_name) {
			this.application_name = application_name;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getAplication_id() {
			return aplication_id;
		}

		public void setAplication_id(Integer aplication_id) {
			this.aplication_id = aplication_id;
		}

		public Long getUser_id() {
			return user_id;
		}

		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}

		public Ticket(String title, String description, Integer aplication_id, Long user_id) {
			super();
			this.title = title;
			this.description = description;
			this.aplication_id = aplication_id;
			this.user_id = user_id;
			this.user_name = user_name;
			this.application_name = application_name;
		}


		public Ticket() {
	    }

}
