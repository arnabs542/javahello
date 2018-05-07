package hw.sprint;

/**
 * 
 * Given an m * n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific Ocean" touches the left and top edges of the matrix and the "Atlantic ocean" 
 * touches the right and bottom edges.
 * 
 * Water can only flow in four directions( up, down, left, or right) from a cell to another one with height 
 * equal or lower.
 * 
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * 
 * Pacific ~~~~~~~~
 * ~	1	2	2	3	5^	*
 * ~	3	2	3	4^	4^	*
 * ~	2	4	5^	3	1	*
 * ~	6^	7^	1	4	5	*
 * ~	5^	1	1	2	4	*
 * ~	*	*	*	*	*	Atlantic
 * 
 * Return:
 * [{0, 4}, {1, 3}, {1, 4}, [2, 2], [3, 0], [3, 1], [4, 0]]
 * (positions with ^ in above matrix)
 * 
 * @author 
 *
 */
public class DFSPacificOceanPath {

}
