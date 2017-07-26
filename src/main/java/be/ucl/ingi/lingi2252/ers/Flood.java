package be.ucl.ingi.lingi2252.ers;
import java.util.List;

public class Flood extends Disaster {
	
	private List<GPSCoordinates> dangerousArea;
	private List <GPSCoordinates> affectedArea;	
	
	public Flood(String distr_name, Boolean occ, List <GPSCoordinates> affectedArea, 
			List<GPSCoordinates> dangerousArea){	
		super(distr_name, occ);
		this.affectedArea = affectedArea;
		this.dangerousArea = dangerousArea;  
	}
	
	public void setDangerousArea(List<GPSCoordinates> dangerousArea) {
		this.dangerousArea = dangerousArea;
	}
	public List<GPSCoordinates> getDangerousArea() {
		return dangerousArea;
	}
	
	@Override
	public void setAffectdArea(List<GPSCoordinates> affectdArea) {
		super.setAffectdArea(affectdArea);
	}
	
	public void displayAffectedFloodAreas() {
		
		for(int i=0; i < affectedArea.size(); i++) {
			//displayonMap
		}
	
	
	}	
	
	/**
     * *
     *
     * @param pos
     * @return
     */
    @Override
    public boolean contains(GPSCoordinates pos) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = affectedArea.size() - 1; i < affectedArea.size(); j = i++) {
            if ((affectedArea.get(i).getLongitude() > pos.getLongitude()) != (affectedArea.get(j).getLongitude() > pos.getLongitude())
                    && (pos.getLatitude() < (affectedArea.get(j).getLatitude() - affectedArea.get(i).getLatitude()) * (pos.getLongitude() - affectedArea.get(i).getLongitude())
                    / (affectedArea.get(j).getLongitude() - affectedArea.get(i).getLongitude()) + affectedArea.get(i).getLatitude())) {
                result = !result;
            }
        }
        return result;
    }

}
