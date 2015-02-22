package util;

/**
 * Created by Ashish on 2/21/2015.
 */
public class InputDataConsole {//implements InputData{
//    @Override
//    public Map<String, Flight> getFlights() {
//        return null;
//    }
//
//    @Override
//    public Map<String, Bag> getBags() {
//        return null;
//    }
//
//    @Override
//    public BiDirectionalGraph getConveyorSystem() {
//        return null;
//    }
//
//    @Override
//    public BiDirectionalGraph readData(InputStream stream) throws IOException {
//        if (stream == null) throw new IllegalArgumentException("Stream can not be null");
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
//        String line;
//        String inputString = null;
//        String[] returnValue;
//        final String NEW_LINE = "\n";
//        while((line = bufferedReader.readLine() ) != null){
//            if(!line.trim().equals(""))
//                inputString += line + NEW_LINE;
//        }
//        assert inputString != null;
//        returnValue = inputString.split("#Section:");
//        return returnValue;
//    }
}
