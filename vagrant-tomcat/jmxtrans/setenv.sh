export JAVA_OPTS="$JAVA_OPTS -javaagent:/vagrant/jmxtrans/jmxtrans-agent-1.0.6.jar=/vagrant/jmxtrans/jmxtrans-agent.xml"
export JAVA_OPTS="$JAVA_OPTS -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled"
export JAVA_OPTS="$JAVA_OPTS -XX:PermSize=128m -XX:MaxPermSize=256m -Xmx1024M"

export JAVA_OPTS="$JAVA_OPTS -javaagent:/vagrant/appd/javaagent.jar" 