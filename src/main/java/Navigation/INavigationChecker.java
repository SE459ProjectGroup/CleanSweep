package main.java.Navigation;

public interface INavigationChecker {

	Boolean CheckCoordinate(Coordinate coordinate);

	int GetWeightedCostToOrigin(Coordinate fromCoordinate);
}
