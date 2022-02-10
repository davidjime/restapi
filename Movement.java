public class Movement {
        private String name;
        private String equipment;
        private String primaryMuscle;
        private String[] auxiliaryMuscles;
        
        public Movement(String name, String equipment, String primaryMuscle) {
            this.name = name;
            this.equipment = equipment;
            this.primaryMuscle = primaryMuscle;
        }
    
        public String GetName() {
            return name;
        }
    
        public String GetEquipment() {
            return equipment;
        }
    
        public String GetPrimaryMuscle() {
            return primaryMuscle;
        }
    }
}
