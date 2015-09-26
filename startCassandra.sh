#!/bin/bash

# Restart cassandra

kill -9  `cat /tmp/cassandra.pid`

/opt/cassandra/bin/cassandra -p /tmp/cassandra.pid

