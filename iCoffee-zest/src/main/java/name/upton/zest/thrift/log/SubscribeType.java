/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package name.upton.zest.thrift.log;



public enum SubscribeType implements org.apache.thrift.TEnum {
  CLOUD(0),
  HDFS(1),
  NETWORK(2),
  ZOOKEEPER(3);

  private final int value;

  private SubscribeType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static SubscribeType findByValue(int value) { 
    switch (value) {
      case 0:
        return CLOUD;
      case 1:
        return HDFS;
      case 2:
        return NETWORK;
      case 3:
        return ZOOKEEPER;
      default:
        return null;
    }
  }
}
