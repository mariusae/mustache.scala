package org.monkey.mustache;

import java.util.Map;
import java.util.ArrayDeque;

public class Context {
  private ArrayDeque stack = new ArrayDeque<Map<String, ?>>();

  Context(Map<String, ?> dictionary) {
    stack.push(dictionary);
  }

  public Object resolve(String name) {
    for (Map<String, ?> dictionary : stack) {
      if (dictionary.containsKey(name))
        return (Object)dictionary.get(name);
    }

    return null;
  }

  public boolean push(String name) {
    Object resolved = resolve(name);

    if (resolved instanceof Map<String, ?>) {
      Map<String, ?> dictionary = (Map<String, ?>)resolved;
      stack.push(dictionary);
      return true;
    } else {
      return false;
    }
  }

  public void pop() {
    stack.pop();
  }
}