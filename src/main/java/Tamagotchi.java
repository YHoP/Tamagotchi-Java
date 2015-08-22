import java.util.*;

public class Tamagotchi {

  private String mPetName, mPetStatus;
  private int mFoodLevel, mPlayLevel, mSleepLevel;
  private Random gNumber = new Random();
  private boolean init = true;


  public Tamagotchi(String userPetName) {
    mPetName = userPetName;
      if(init){
        initLevel();
      }
    }

  public String getPetStatus(){
    mPetStatus = "Food level: \t" + mFoodLevel + "<br>" +
                "Play level: \t" + mPlayLevel + "<br>" +
                "Sleep level: \t" + mSleepLevel + "<br>";
    return mPetStatus;
  }

  public void initLevel(){
    mFoodLevel = gNumber.nextInt(10) + 1;
    mPlayLevel = gNumber.nextInt(10) + 1;
    mSleepLevel = gNumber.nextInt(10) + 1;
    init = false;
  }

  public String getName(){
    return mPetName;
  }


  public boolean isAlive(){
    if(mFoodLevel <= 0 || mPlayLevel <= 0 || mSleepLevel <= 0){
      return false;
    }
    else {
      return true;
    }
  }

  public void feed() {
    mFoodLevel += 2;
    mPlayLevel -= 1;
    mSleepLevel -= 2;
  }

  public void play() {
    mPlayLevel += 3;
    mFoodLevel -= 2;
    mSleepLevel -= 1;
  }

  public void sleep() {
    mSleepLevel += 4;
    mFoodLevel -= 3;
    mPlayLevel -= 2;
  }

}
