README.txt

Main files: 
-- needed: need to modify in bluetooth merging

welcomeActivity  		initial display 
loginActivity.java 		promtp for login info and check
mainActicty.java 		create the nevigation drawer 
NavigationDrawerFragment. java 	create the nevigation drawer
menuFragment1.java 		display the current reading -- needed in bluetooth merging
menuFragment2.java 		display daily stat 
gridAdapter.java 		costomized addaptor to push the dot maxtrix 
						created in menuFragment2 to a gridview
menuFragment3.java		display area map



GPSTracker.java 		object that keeps track of GPS (from open source)
can be used as: 
//        GPSTracker gps = new GPSTracker(MyActivity.this);
//        if(!gps.canGetLocation()) {
//            gps.showSettingsAlert();
//        }
//
//        double latitude = gps.getLatitude();
//        double longitude = gps.getLongitude();
//        Log.v("MyActivity", "latitude: " + latitude + " longitude: " + longitude);

// It somehow worked without setting service but the location update has to be 
// in main thread otherwise I'll get an error. 

