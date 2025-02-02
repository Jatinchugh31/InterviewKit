package datastructure.src.dp;

public class RainTrapWater {


    public static void main(String[] args) {
        int arr[] = {1,8,6,2,5,4,8,3,7};
        System.out.println(trap(arr));
        System.out.println(trap2(arr));
        System.out.println(trap3(arr));



    }

    public static int trap(int[] height) {
        int n = height.length;
        int res = 0;
        for (int i = 1; i < n - 1; i++) {

            int leftMax = 0, rightMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }

            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            res += Math.min(leftMax, rightMax) - height[i];
        }
        return res;
    }

    public static int trap2(int[] height) {

        int n = height.length;
        int leftMax[] = new int[n];
        int rightMax[] = new int[n];
        int temp = 0;

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);

        }
        int water = 0;
        for (int i = 1; i < n - 1; i++) {
            water += Math.max(0, Math.min(leftMax[i], rightMax[i]) - height[i]);
        }
        return water;
    }

    private static int trap3( int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int l_max=0,r_max=0;
        int res=0;
        while (left <= right) {
            if(r_max <=l_max)
            {
                res+=Math.max(0,r_max-height[right]);
                r_max=Math.max(r_max,height[right]);
                right--;
            }else {
                res+=Math.max(0,l_max-height[left]);
                l_max=Math.max(l_max,height[left]);
                left++;
            }
        }
        return res;
    }
}
