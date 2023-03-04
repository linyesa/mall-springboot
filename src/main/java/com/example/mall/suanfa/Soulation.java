package com.example.mall.suanfa;

import io.swagger.models.auth.In;

import java.util.HashMap;
import java.util.Map;


class Soulation {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hashmap=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;++i){
            if(hashmap.containsKey(target-nums[i])){
                return new int[]{hashmap.get(target-nums[i]),i};
            }
            hashmap.put(nums[i],i);
        }
        return new int[0];
    }
    public int finalValueAfterOperations(String[] operations) {
        int i=0;
        for(String item:operations){
            if("X++".equals(item) || "++X".equals(item)){
                ++i;
            }else{
                i--;
            }
        }
        return i;
    }
    public static void test(){
        System.out.println("test static tset");
    }
    public static void reverseString(char[] s){
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
        for(char item:s){
            System.out.println(item);
        }
    }


    public static void main(String[] args) {
        int[] arrays={2,7,11,15};
        test();
        for(int sumItem:twoSum(arrays,9)){
            System.out.println(sumItem);
        }
        char[] chars={'a','a','n','n','a','h'};
        reverseString(chars);
    }
}

