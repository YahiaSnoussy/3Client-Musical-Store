<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 */
class User
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $username;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $email;

    /**
     * @ORM\OneToMany(targetEntity=Product::class, mappedBy="user", orphanRemoval=true)
     */
    private $myproducts;

    public function __construct()
    {
        $this->myproducts = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setUsername(string $username): self
    {
        $this->username = $username;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    /**
     * @return Collection|Product[]
     */
    public function getMyproducts(): Collection
    {
        return $this->myproducts;
    }

    public function addMyproduct(Product $myproduct): self
    {
        if (!$this->myproducts->contains($myproduct)) {
            $this->myproducts[] = $myproduct;
            $myproduct->setUser($this);
        }

        return $this;
    }

    public function removeMyproduct(Product $myproduct): self
    {
        if ($this->myproducts->removeElement($myproduct)) {
            // set the owning side to null (unless already changed)
            if ($myproduct->getUser() === $this) {
                $myproduct->setUser(null);
            }
        }

        return $this;
    }
}
