package net.bican.wordpress.configuration;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * 
 * A composite configuration class that combines preferences and command line.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class WpCliConfiguration {

  private CompositeConfiguration config = null;

  /**
   * @param args
   *          Command line arguments
   * @param options
   *          Command line options
   * @param cl
   *          Calling class
   * @throws ParseException
   */
  public WpCliConfiguration(String[] args, Options options, Class<?> cl)
      throws ParseException {
    Collection<Configuration> confs = new ArrayList<Configuration>();
    confs.add(new CliConfiguration(args, options));
    confs.add(new PreferencesConfiguration(cl));
    config = new CompositeConfiguration(confs);
  }

  /**
   * @param key
   *          Option key to check
   * @return <code>true</code> if the configuration contains the key
   */
  public boolean hasOption(String key) {
    return config.containsKey(key);
  }

  /**
   * @param key
   *          Option key to retrieve
   * @return Value of the option key
   */
  public String getOptionValue(String key) {
    return config.getString(key);
  }

}
