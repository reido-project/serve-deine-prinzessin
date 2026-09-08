package sdp.modules.requirement;

public interface Requirement {
    Boolean[] require();

    default boolean check(){
        for(Boolean satisfied : require()){
            if(!satisfied){
                return false;
            }
        }
        return true;
    }
}
