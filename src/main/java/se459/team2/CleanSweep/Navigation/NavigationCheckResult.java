package se459.team2.CleanSweep.Navigation;

public class NavigationCheckResult {

	private Boolean canNavigate = false;
	
	private Boolean canMoveUp = false;
	
	private Boolean canMoveRight = false;
	
	private Boolean canMoveLeft = false;
	
	private Boolean canMoveDown = false;

	/**
	 * @return the canNavigate
	 */
	public Boolean getCanNavigate() {
		return canNavigate;
	}

	/**
	 * @param canNavigate the canNavigate to set
	 */
	public void setCanNavigate(Boolean canNavigate) {
		this.canNavigate = canNavigate;
	}

	/**
	 * @return the canMoveUp
	 */
	public Boolean getCanMoveUp() {
		return canMoveUp;
	}

	/**
	 * @param canMoveUp the canMoveUp to set
	 */
	public void setCanMoveUp(Boolean canMoveUp) {
		this.canMoveUp = canMoveUp;
	}

	/**
	 * @return the canMoveRight
	 */
	public Boolean getCanMoveRight() {
		return canMoveRight;
	}

	/**
	 * @param canMoveRight the canMoveRight to set
	 */
	public void setCanMoveRight(Boolean canMoveRight) {
		this.canMoveRight = canMoveRight;
	}

	/**
	 * @return the canMoveLeft
	 */
	public Boolean getCanMoveLeft() {
		return canMoveLeft;
	}

	/**
	 * @param canMoveLeft the canMoveLeft to set
	 */
	public void setCanMoveLeft(Boolean canMoveLeft) {
		this.canMoveLeft = canMoveLeft;
	}

	/**
	 * @return the canMoveDown
	 */
	public Boolean getCanMoveDown() {
		return canMoveDown;
	}

	/**
	 * @param canMoveDown the canMoveDown to set
	 */
	public void setCanMoveDown(Boolean canMoveDown) {
		this.canMoveDown = canMoveDown;
	}

	private boolean didNavigateToOrgin = false;
	
	public boolean getDidNavigateToOrgin() {
		return didNavigateToOrgin;
	}
	
	public void setDidNavigateToOrgin(boolean b) {
		didNavigateToOrgin = b;
	}
	
	
	
}
