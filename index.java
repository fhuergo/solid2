
import java.lang.reflect.Method;

// Hello programmer!
//
// Rewrite these classes so you resolve where each of these
// principles have gone wrong:
//
// Single Responsibility Principle
// Liskov Principle
// Interface Segregation Principle
// Dependency Inversion Principle
//
// What to resolve
// SRP:    Move a method that doesn't seme to belong to class to the
//         IfYouMoveAMethodPutItInHere class
// Liskov: Comment out the offending method! Or rather, the obsolete
//         method.
// ISP:    Remove unnecessary method. You might have to do it in more
//         than one place.
// DIP:    Refactor an instance of a non-interface's methods being
//         passed into a method. Utilize the
//         IfYouNeedANewInterfaceUseThisOne interface
//
// Hit "Run" above to see how you're doing!
//
// Where's Open Closed Principle you might ask? That's coming up next
// :)




// These will come in handy!

class IfYouMoveAMethodPutItInHere {

}

interface IfYouNeedANewInterfaceUseThisOne {

}




// The "monolith"

//// Abstract classes

abstract class StorefrontModel extends CompanyWideDesign implements ShinyMouseOverInterface {
	private final boolean shouldButtonsBeRounded = true;

  private boolean shouldAppearShiny = false;

	public String getFont() {
		if (this.getFont() != "Arial") {
			return this.getFont();
		}
		return "Helvetica";
	}

  protected boolean shouldButtonsBeRounded() {
    return this.shouldButtonsBeRounded;
  }

  protected boolean shouldAppearShiny() {
    return this.shouldAppearShiny;
  }

	public void addShine() {
		this.shouldAppearShiny = true;
	}

	public void addBlinkingShine() {
		return;
	}
}

abstract class CompanyWideDesign {
	private final String fontFace = "Verdana";

	private final String fontSize = "12px";

  public String getFont() {
      return this.fontFace;
  }

	protected String getFontSize() {
		return this.fontSize;
  }
}

//// Classes

class User {

}

class Item {
  private String type;
  public Item() {
    this.type = "type";
  }
  public String getType() {
    return this.type;
  }
}

class CheckoutPageModel extends StorefrontModel {
	protected String colorCode;

	protected String topText;

	public CheckoutPageModel(String topText, String colorCode) {
		this.topText   = topText;
		this.colorCode = colorCode;
  }

  public User submit(User user, Item item) {
    switch (item.getType()) {
      case "handbag":
        return this.submitHandbag(user);
      case "shoes":
        return this.submitShoes(user);
      default:
        return null;
    }
  }

  public String getTopText() {
    return this.topText;
  }

  public String getColorCode() {
    return this.colorCode;
  }

  public boolean shouldSendUserFeedbackToProduct(UserFeedback feedback) {
    if (feedback.isInteresting()) return true;
    if (feedback.isSpam()) return false;
    return false;
  }

  public int getByteSizeOfStringFields() {
      return (this.topText + this.colorCode).length();
  }

  private User submitHandbag(User user) {
    return user;
  }

  private User submitShoes(User user) {
    return user;
  }
}

class UserFeedback {
	private String feedback;

	public UserFeedback(String feedback) {
		this.feedback = feedback;
	}

	public boolean isInteresting() {
		return !this.isSpam();
	}

	public boolean isSpam() {
		return (this.feedback).toLowerCase().contains("buy today!");
	}
}

//// Interface

interface ShinyMouseOverInterface {
	public void addShine();
	public void addBlinkingShine();
}



// the answers are below, careful!


class Main {
  public static void main(String[] args) {

    // Single Responsibility Principle
    System.out.println("Single Responsibility Principle");
    Method methodToFind = null;
    try {
      methodToFind = CheckoutPageModel.class.getMethod("getByteSizeOfStringFields", (Class<?>[]) null);
      System.out.println("   failing");
    } catch (NoSuchMethodException | SecurityException e) {
      // Your exception handling goes here
      try {
        methodToFind = IfYouMoveAMethodPutItInHere.class.getMethod("getByteSizeOfStringFields", (Class<?>[]) null);
      System.out.println("   passing!");
      } catch (NoSuchMethodException | SecurityException f) {
        System.out.println("Single Responsibility Principle - You correctly removed the method, now put it in class IfYouRemoveAMethodPutItInHere");
      }
    }

    // Liskov Principle
    System.out.println("Liskov Principle");
    try {
      methodToFind = CompanyWideDesign.class.getMethod("getFont", (Class<?>[]) null);
      System.out.println(
        "   failing"
      );
    } catch (NoSuchMethodException | SecurityException g) {
      System.out.println(
        "   passing!"
      );
    }

    // Interface Segregation Principle
    System.out.println("Interface Segregation Principle");
    try {
      methodToFind = StorefrontModel.class.getMethod("addBlinkingShine", (Class<?>[]) null);
      System.out.println("   failing");
    } catch (NoSuchMethodException | SecurityException h) {
      System.out.println("   passing!");
    }

    // Dependency Inversion Principle
    System.out.println("Dependency Inversion Principle");
    Class<?> classObj = UserFeedback.class;
    if (IfYouNeedANewInterfaceUseThisOne.class.isAssignableFrom(classObj)) {

      // has interface implemented
      System.out.println(IfYouNeedANewInterfaceUseThisOne.class.isAssignableFrom(classObj));
      System.out.println("   has interface...");

      // interface has correct methods
      try {
        IfYouNeedANewInterfaceUseThisOne.class.getMethod("isInteresting", (Class<?>[]) null);
        IfYouNeedANewInterfaceUseThisOne.class.getMethod("isSpam", (Class<?>[]) null);
        System.out.println("   interface has methods, good...");
      } catch (NoSuchMethodException | SecurityException i) {
        System.out.println("   failing");
        System.out.println("   interface does not have methods!");
      }
    } else {
      System.out.println("   failing");
      System.out.println("   Implement the implement-me interface in the correct class.");
    }
  }
}
