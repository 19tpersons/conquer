import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * This is the series of buttons that appear when a user hovers over the icon of a planet card.
 * @author DAT Software Engineering
 */
public class CardButtonPanel extends JPanel {
	private JPanel generalPanel = new JPanel(), fightPhasePanel = new JPanel();
	private JButton fightBtn;
	private Button popGrowthBtn, soldiersBtn;
	private JLabel nextPopGrowthPrice, nextPopGrowthRate;
	public AddSoldiersModal soldiersModal = new AddSoldiersModal();

	public CardButtonPanel(int width, int height, PlayerStats stats, Card card) {
		this.setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(width, height));
		
		//This sets up the panel
		generalPanel.setBackground(new Color(255,255,255,50));
		generalPanel.setPreferredSize(new Dimension(width, height));

		
		//This will allow users' to add soldiers to their armies
		soldiersBtn = new Button("Add Soldiers");
		soldiersBtn.setFont(new Font("Arial", Font.BOLD, 18));
		soldiersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				 //soldiersModal = new AddSoldiersModal(stats, card);
				soldiersModal.setUpModal(stats, card);
				stats.getUser().setModal(soldiersModal);
				generalPanel.setVisible(false);
			}

		});
		generalPanel.add(soldiersBtn);
		
		
		//This will allow users' to modify a planet
		popGrowthBtn = new Button("Pop. Growth " + (PopulationGrowth.getNextRate(card.getPopChangeRate()) * 100) + "%");
		popGrowthBtn.setFont(new Font("Arial", Font.BOLD, 18));
		popGrowthBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				System.out.println("Increasing " + card.getName() + "'s population growth rate.");
				
				double nextPopGrowthRateDouble = PopulationGrowth.getNextRate(card.getPopChangeRate());
				int cost = PopulationGrowth.getGrowthPrice(card.getPop());
				
				if (stats.getResource() >= cost) {
					stats.removeResources(cost);
					card.setPopChangeRate(nextPopGrowthRateDouble);
					card.addPop((int) (card.getPop() * nextPopGrowthRateDouble));
					stats.getUser().calculatePop();
					stats.getUser().updateSideNav();
					
					String popGrowth = "Pop. Growth " + (PopulationGrowth.getNextRate(card.getPopChangeRate()) * 100) + "%";
					popGrowthBtn.setText(popGrowth);
					
					String price = "Price: " + PopulationGrowth.getGrowthPrice(card.getPop());
					nextPopGrowthPrice.setText(price);
					String rate = "Cur. Rate: " + (card.getPopChangeRate() * 100) + "%";
					nextPopGrowthRate.setText(rate);
					
					System.out.println("Increasing " + card.getName() + "'s population growth rate.");
					System.out.println(popGrowth + "\n" + price + "\n" + rate);
				}
			}
		});
		generalPanel.add(popGrowthBtn);
		
		nextPopGrowthPrice = new JLabel("Price: " + PopulationGrowth.getGrowthPrice(card.getPop()));
		generalPanel.add(nextPopGrowthPrice);
		nextPopGrowthRate = new JLabel("Cur. Rate: " + (card.getPopChangeRate() * 100) + "%");
		generalPanel.add(nextPopGrowthRate);
		
		//This sets up the panel
		fightPhasePanel.setBackground(new Color(255,255,255,50));
		fightPhasePanel.setPreferredSize(new Dimension(width, height));
		
		//This will allow users' to fight each other.
		fightBtn = new JButton("Fight");
		fightBtn.setFont(new Font("Arial", Font.BOLD, 18));
		fightBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				PlayerStats enemyStats = RootGameControl.getCurUser().getStats();
				FightModal modal = new FightModal(enemyStats, stats, card);
				enemyStats.getUser().setModal(modal); //We need to put the modal on the enemy's screen.
				fightPhasePanel.setVisible(false);
			}
		});
		fightPhasePanel.add(fightBtn);
	}
	
	/**
	 * If the turn is currently in the fight phase, then we need to show those specific buttons
	 */
	public void showFightPhase() {
		remove(generalPanel);
		add(fightPhasePanel);
		fightPhasePanel.setVisible(true);
		revalidate();
		repaint();
	} 
	
	/**
	 * This will show the buttons that the user can click at any point during their turn.
	 */
	public void showGeneral() {
		remove(fightPhasePanel);
		generalPanel.setVisible(true);
		add(generalPanel);
		revalidate();
		repaint();
	}
	
	public JPanel getGeneral() {
		return this.generalPanel;
	}
	
	public JPanel getFightPhasePanel() {
		return this.fightPhasePanel;
	}
}
