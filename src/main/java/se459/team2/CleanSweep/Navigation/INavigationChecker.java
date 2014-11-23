package se459.team2.CleanSweep.Navigation;

public interface INavigationChecker {

	NavigationCheckResult CheckCoordinate(Coordinate coordinate);

	int GetWeightedCostToOrigin(Coordinate fromCoordinate);

}
