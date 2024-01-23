package com.example.ltm.Model.BO;



import com.example.ltm.Model.Bean.File;
import com.example.ltm.Model.DAO.ShowProfileDAO;

import java.util.List;

public class ShowProfileBO {
public static List<File> GetProcessFile(int userId){return ShowProfileDAO.GetProcessedFile(userId);
}
}
