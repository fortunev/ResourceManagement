package com.spring.dao;

import java.util.List;
import com.spring.model.Cell;

public interface CellDAO {
	long save(Cell cell, int col_id, int r_id);
    long add_to_Resource(int cell_id, int r_id);
    long add_to_Columns(int cell_id, int col_id);
    long add_to_Res_Col (int cell_id, int r_id, int col_id);
    Cell get(int id);
    List<Cell> list();
    List<Cell> list_resource_id(int r_id); 
    List<Cell> list_column_id(int col_id); 
    void update(int cell_id, Cell col);
    void delete(int cell_id);
}
