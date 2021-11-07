import pandas as pd
import matplotlib.pyplot as plt

read_file = pd.read_csv(r'log/log.txt')
read_file.to_csv(r'log/log.csv', index=None)

base_df = pd.read_csv("log/log.csv", names=['ms', 'location'])

print(base_df)

y = base_df["ms"]
x = base_df["location"]

plt.title(
    "scatterplot representing the total time taken to load image from url and cache")
plt.scatter(x, y)

plt.xlabel('ms')
plt.ylabel('location')

plt.show()
