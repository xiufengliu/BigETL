#!/bin/bash

# Restart cassandra


/opt/kairosdb/bin/kairosdb.sh stop >/dev/null 2>&1
/opt/kairosdb/bin/kairosdb.sh start >/dev/null 2>&1
