package com.fdmgroup.projectweek4;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Runner {

	public static void main(String[] args) {

		// 1. Remember to change the persistence unitname at the top so it is the same
		// as the project name
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectweek4");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		// 2. Be careful with each object and make sure you are creating each object and
		// setting its properties completely. For each object, make sure the variable
		// name is correct in each block and it has a unique primary key value.
		// Be careful with copy and paste!

		Team team1 = new Team();
		team1.setTeamId(1);
		team1.setTeamName("LIV");

		// Then create the other team objects

		Player player1 = new Player();
		player1.setPlayerId(1);
		player1.setPlayerName("Salah");
		player1.setTeam(team1);

		// then other player objects

		
		entityTransaction.begin();

		// 3. Perform the deletes. Remember to delete the child entities before the
		// parents

		TypedQuery<Player> queryPlayer = entityManager.createQuery("Delete from Player", Player.class);
		queryPlayer.executeUpdate();

		TypedQuery<Team> queryTeam = entityManager.createQuery("Delete from Team", Team.class);
		queryTeam.executeUpdate();

		// 4. When you persist the objects make sure you persist them in the opposite
		// order to the order where you delete them.

		entityManager.persist(team1);
		entityManager.persist(player1);

		// remember to commit!
		entityTransaction.commit();

		// It is worth displaying the tables of data here so we can see the data after
		// the runner has been run
		queryTeam = entityManager.createQuery("Select t from Team t", Team.class);
		List<Team> listOfTeams = queryTeam.getResultList();
		for (Team eachTeam : listOfTeams) {
			System.out.print(eachTeam.getTeamId());
			System.out.println(", " + eachTeam.getTeamName());

		}

		// 5. Close the entityManager at the end of the file. This will prevent database
		// connection problems.
		entityManager.close();

	}

}
